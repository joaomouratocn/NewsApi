package br.com.devjmcn.newsapp.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import br.com.devjmcn.newsapp.repository.retrofit.NewsApiService
import br.com.devjmcn.newsapp.repository.retrofit.model.Article
import java.io.IOException

class PagingNews(private val keyWords:String, private val service: NewsApiService) : PagingSource<Int, Article>() {
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
            val response = service.searchNews(
                keyWords = keyWords,
                page = position,
                pageSize = pageSize,
            )

            val newResponse = response.body()
            val articles = newResponse?.articles?.filter { it.title != "[Removed]" } ?: emptyList()
            val totalResult = newResponse?.totalResults ?: 0

            val nextKey = if (articles.isEmpty() || position * pageSize >= totalResult) {
                null
            } else {
                position + 1
            }
            LoadResult.Page(
                data = articles,
                prevKey = if (position == 1) null else position -1,
                nextKey = nextKey
            )
        }catch (exception:IOException){
            LoadResult.Error(exception)
        }
    }
}