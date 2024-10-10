package br.com.devjmcn.newsapp.data.retrofit

import br.com.devjmcn.newsapp.data.retrofit.model.ResponseModel
import retrofit2.http.GET

interface TopHeadLineService {
    @GET("")
    suspend fun getTopHeadLine(): ResponseModel
}