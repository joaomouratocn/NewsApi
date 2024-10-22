package br.com.devjmcn.newsapp.ui.fragmentNews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import br.com.devjmcn.newsapp.repository.PagingNews
import br.com.devjmcn.newsapp.repository.retrofit.model.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewFragmentViewModel @Inject constructor(pageNews: PagingNews) : ViewModel() {
    private val _news: Flow<PagingData<Article>> = Pager(
        config = PagingConfig(20, enablePlaceholders = false),
        pagingSourceFactory = { pageNews }
    ).flow.cachedIn(viewModelScope)

    val news: Flow<PagingData<Article>> = _news
}