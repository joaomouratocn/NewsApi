package br.com.devjmcn.newsapp.repository.retrofit

import br.com.devjmcn.newsapp.BuildConfig
import br.com.devjmcn.newsapp.repository.retrofit.model.ResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("everything")
    suspend fun searchNews(
        @Query("q") keyWords: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("language") language: String = "pt",
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY

    ): retrofit2.Response<ResponseModel>
}