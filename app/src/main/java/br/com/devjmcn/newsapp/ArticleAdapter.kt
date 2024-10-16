package br.com.devjmcn.newsapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView.ScaleType.CENTER
import android.widget.ImageView.ScaleType.CENTER_CROP
import android.widget.ImageView.ScaleType.FIT_CENTER
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.devjmcn.newsapp.data.retrofit.model.Article
import br.com.devjmcn.newsapp.databinding.ItemLayoutNewsBinding
import br.com.devjmcn.newsapp.util.convertDate
import coil3.load
import coil3.request.crossfade
import coil3.request.error
import coil3.request.placeholder
import java.util.ResourceBundle

class ArticleAdapter : PagingDataAdapter<Article, ArticleAdapter.ArticleViewHolder>(
    ARTICLE_COMPARATOR
) {

    companion object {
        val ARTICLE_COMPARATOR = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.title == newItem.title && oldItem.source.id == newItem.source.id
            }

        }
    }

    class ArticleViewHolder(private val binding: ItemLayoutNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun inflate(parent: ViewGroup): ArticleViewHolder {
                val binding = ItemLayoutNewsBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return ArticleViewHolder(binding)
            }
        }

        fun bind(article: Article) {
            with(binding) {
                txvTitle.text = article.title
                txvSource.text = article.source.name
                txvPublishIn.text = article.publishedAt.convertDate()
                imgCover.load(article.urlToImage) {
                    crossfade(true)
                    placeholder(R.drawable.place_holder)
                    error(R.drawable.error_warning)
                }
            }
        }
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = getItem(position)
        article?.let { holder.bind(article) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder.inflate(parent)
    }
}