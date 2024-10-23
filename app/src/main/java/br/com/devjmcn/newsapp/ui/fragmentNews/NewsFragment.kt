package br.com.devjmcn.newsapp.ui.fragmentNews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import br.com.devjmcn.newsapp.R
import br.com.devjmcn.newsapp.databinding.FragmentSearchNewsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsFragment : Fragment() {
    private val newFragmentViewModel: NewFragmentViewModel by viewModels()

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
        with(binding) {
            rcvNews.adapter = adapter
            btnSearch.setOnClickListener {
                val searchText = edtSearchNews.text.toString()
                searchNews(searchText)
            }
        }
        lifecycleScope.launch {
            newFragmentViewModel.news.collectLatest { pagingData ->
                if (pagingData != null) {
                    adapter.submitData(pagingData)
                }
            }
        }
    }

    private fun searchNews(searchText: String) {
        if (searchText.isNotBlank()) {
            newFragmentViewModel.searchNews(searchText)
            return
        }
        Toast.makeText(
            requireContext(),
            getString(R.string.str_insert_topic_for_search),
            Toast.LENGTH_SHORT
        ).show()
    }
}