package br.com.devjmcn.newsapp.di

import br.com.devjmcn.newsapp.repository.PagingNews
import br.com.devjmcn.newsapp.repository.retrofit.NewsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PageNewsProvider{
    @Provides
    @Singleton
    fun providePageNews(newsApiService: NewsApiService): PagingNews {
        return PagingNews(service = newsApiService)
    }
}