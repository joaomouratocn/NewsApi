package br.com.devjmcn.newsapp.data.retrofit

import br.com.devjmcn.newsapp.data.retrofit.RetrofitInstance.API_KEY
import br.com.devjmcn.newsapp.data.retrofit.model.ResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface TopHeadLineService {
    @GET("everything")
    suspend fun getTopHeadLine(
        @Query("q") keyWords: String,
        @Query("page") page:Int,
        @Query("pageSize") pageSize:Int,
        @Query("language") language: String = "pt",
        @Query("apiKey") apiKey: String = API_KEY
    ): retrofit2.Response<ResponseModel>
}