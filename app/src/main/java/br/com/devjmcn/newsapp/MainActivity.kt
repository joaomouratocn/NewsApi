package br.com.devjmcn.newsapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import br.com.devjmcn.newsapp.data.PagingNews
import br.com.devjmcn.newsapp.data.retrofit.RetrofitInstance
import br.com.devjmcn.newsapp.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val adapter by lazy{
        ArticleAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initConfig()
    }

    private fun initConfig() {
        val articles = Pager(
            config = PagingConfig(20, enablePlaceholders = false),
            pagingSourceFactory = { PagingNews(
                keyWords = "SpaceX",
                RetrofitInstance.service
            ) }
        ).flow.cachedIn(lifecycleScope)

        binding.rcvNews.adapter = adapter

        lifecycleScope.launch {
            articles.collectLatest {pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }
}