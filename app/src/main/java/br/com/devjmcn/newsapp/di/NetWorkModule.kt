package br.com.devjmcn.newsapp.di

import br.com.devjmcn.newsapp.repository.retrofit.NewsApiService
import br.com.devjmcn.newsapp.util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(Singleton::class)
object NetWorkModule {
    @Provides
    @Singleton
    private fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideService(retrofit: Retrofit): NewsApiService {
        return retrofit.create(NewsApiService::class.java)
    }
}