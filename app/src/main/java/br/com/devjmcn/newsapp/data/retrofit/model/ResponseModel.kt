package br.com.devjmcn.newsapp.data.retrofit.model

data class ResponseModel(
    val status:String,
    val totalResults:Int,
    val articles:List<Article>
)