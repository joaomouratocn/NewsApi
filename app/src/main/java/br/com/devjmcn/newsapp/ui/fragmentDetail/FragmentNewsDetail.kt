package br.com.devjmcn.newsapp.ui.fragmentDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import br.com.devjmcn.newsapp.databinding.FragmentNewsDetailBinding

class FragmentNewsDetail : Fragment() {
    private val args : FragmentNewsDetailArgs by navArgs()

    private val binding by lazy {
        FragmentNewsDetailBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        init()
        return binding.root
    }

    private fun init(){
        binding.webView.webViewClient = WebViewClient()
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.loadUrl(args.url)
    }
}