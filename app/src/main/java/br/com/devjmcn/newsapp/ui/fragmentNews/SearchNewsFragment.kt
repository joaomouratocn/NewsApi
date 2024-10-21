package br.com.devjmcn.newsapp.ui.fragmentNews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import br.com.devjmcn.newsapp.repository.PagingNews
import br.com.devjmcn.newsapp.data.retrofit.RetrofitInstance
import br.com.devjmcn.newsapp.databinding.FragmentSearchNewsBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SearchNewsFragment : Fragment() {
    private val binding by lazy {
        FragmentSearchNewsBinding.inflate(layoutInflater)
    }

    private val adapter by lazy {
        ArticleAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initConfig()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    private fun initConfig() {
        val articles = Pager(
            config = PagingConfig(20, enablePlaceholders = false),
            pagingSourceFactory = {
                PagingNews(
                    keyWords = "ultimas noticias",
                    RetrofitInstance.service
                )
            }
        ).flow.cachedIn(lifecycleScope)

        with(binding) {
            rcvNews.adapter = adapter
        }

        lifecycleScope.launch {
            articles.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }
}