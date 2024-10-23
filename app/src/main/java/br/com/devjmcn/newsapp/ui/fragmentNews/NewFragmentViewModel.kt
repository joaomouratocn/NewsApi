package br.com.devjmcn.newsapp.ui.fragmentNews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import br.com.devjmcn.newsapp.repository.PagingNews
import br.com.devjmcn.newsapp.repository.retrofit.NewsApiService
import br.com.devjmcn.newsapp.repository.retrofit.model.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewFragmentViewModel @Inject constructor(private val newsApiService: NewsApiService) :
    ViewModel() {

    private val _searchText: MutableStateFlow<String> = MutableStateFlow("Notícias")

    private val _news: MutableStateFlow<PagingData<Article>?> = MutableStateFlow(null)
    val news: StateFlow<PagingData<Article>?> = _news


    init {
        viewModelScope.launch {
            _searchText.collectLatest { query ->
                val newPager = Pager(
                    config = PagingConfig(pageSize = 20, enablePlaceholders = false),
                    pagingSourceFactory = {
                        PagingNews(
                            keyWords = query,
                            service = newsApiService
                        )
                    }
                ).flow.cachedIn(viewModelScope)

                newPager.collectLatest { pagingData ->
                    _news.value = pagingData
                }
            }
        }
    }

    fun searchNews(searchText: String) {
        _searchText.value = searchText
    }
}