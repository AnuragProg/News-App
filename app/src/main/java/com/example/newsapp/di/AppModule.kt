package com.example.newsapp.di

import com.example.newsapp.data.remotedatasource.NewsService
import com.example.newsapp.data.repository.NewsRepositoryImpl
import com.example.newsapp.data.utils.NewsServiceUtils.BASE_URL
import com.example.newsapp.domain.repository.NewsRepository
import com.example.newsapp.domain.usecases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesNewsService(): NewsService{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsService::class.java)
    }

    @Provides
    @Singleton
    fun providesNewsRepository(newsService: NewsService): NewsRepository{
        return NewsRepositoryImpl(newsService = newsService)
    }

    @Provides
    @Singleton
    fun providesNewsUseCase(newsRepository: NewsRepository): NewsUseCases{
        return NewsUseCases(
            getTopHeadlinesUseCase = GetTopHeadlinesUseCase(newsRepository = newsRepository),
            getSearchedNewsUseCase = GetSearchedNewsUseCase(newsRepository = newsRepository),
            getSportsHeadlinesUseCase = GetSportsHeadlinesUseCase(newsRepository = newsRepository),
            getBusinessHeadlinesUseCase = GetBusinessHeadlinesUseCase(newsRepository = newsRepository),
            getScienceHeadlinesUseCase = GetScienceHeadlinesUseCase(newsRepository = newsRepository),
            getTechnologyHeadlinesUseCase = GetTechnologyHeadlinesUseCase(newsRepository = newsRepository),
            getHealthHeadlinesUseCase = GetHealthHeadlinesUseCase(newsRepository = newsRepository),
            getEntertainmentHeadlinesUseCase = GetEntertainmentHeadlinesUseCase(newsRepository = newsRepository)
        )
    }

}