package br.com.devjmcn.newsapp.data.retrofit

import br.com.devjmcn.newsapp.BuildConfig
import br.com.devjmcn.newsapp.data.retrofit.model.ResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("everything")
    suspend fun searchNews(
        @Query("q") keyWords: String = "Notícias",
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("language") language: String = "pt",
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY

    ): retrofit2.Response<ResponseModel>
}