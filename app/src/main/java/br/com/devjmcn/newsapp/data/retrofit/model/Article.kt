package br.com.devjmcn.newsapp.data.retrofit.model

data class Article(
    val source: Source,
    val author:String,
    val title:String,
    val description:String,
    val url:String,
    val urlToImage:String,
    val publishedAt:String,
    val content:String,
)