package br.com.devjmcn.newsapp.data.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val BASE_URL = "https://newsapi.org/v2/"
    val API_KEY = "239f17c8627d4ce3b828ff72cf12d43a"

    val service by lazy {
        retrofit.create(TopHeadLineService::class.java)
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}