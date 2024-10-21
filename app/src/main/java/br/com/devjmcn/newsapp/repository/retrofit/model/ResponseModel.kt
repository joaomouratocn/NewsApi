package br.com.devjmcn.newsapp.repository.retrofit.model

data class ResponseModel(
    val status:String,
    val totalResults:Int,
    val articles:List<Article>
)