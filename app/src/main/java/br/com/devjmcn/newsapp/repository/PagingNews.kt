package br.com.devjmcn.newsapp.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import br.com.devjmcn.newsapp.repository.retrofit.NewsApiService
import br.com.devjmcn.newsapp.repository.retrofit.model.Article
import br.com.devjmcn.newsapp.repository.retrofit.model.ResponseModel
import java.io.IOException

class PagingNews(private val keyWords: String, private val service: NewsApiService) :
    PagingSource<Int, Article>() {
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val position = params.key ?: 1
        val pageSize = params.loadSize

        return try {
            val response = service.searchNews(keyWords, position, pageSize)

            val newResponse = response.body()

            val articles = applyFilters(newResponse)

            val totalResult = newResponse?.totalResults ?: 0

            val nextKey = if (articles.isEmpty() || position * pageSize >= totalResult) {
                null
            } else {
                position + 1
            }
            LoadResult.Page(
                data = articles,
                prevKey = if (position == 1) null else position - 1,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        }
    }

    private fun applyFilters(newResponse: ResponseModel?): List<Article> {
        val articles = newResponse?.articles?.filter { it.title != "[Removed]" }
            ?.sortedByDescending { it.publishedAt } ?: emptyList()
        return articles
    }
}