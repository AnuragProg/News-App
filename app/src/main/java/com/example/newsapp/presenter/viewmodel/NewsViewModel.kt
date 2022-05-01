package com.example.newsapp.presenter.viewmodel

import androidx.lifecycle.ViewModel
import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.usecases.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
): ViewModel() {

    var searchQuery : String = ""

    suspend fun getTopHeadlines(country: String = "in", page: Int): List<Article>{
        return newsUseCases.getTopHeadlinesUseCase(country, page)
    }

    suspend fun getSearchedNews(query: String, page: Int) : List<Article>{
        return newsUseCases.getSearchedNewsUseCase(query, page)
    }

    suspend fun getSportsHeadlines(page:Int): List<Article>{
        return newsUseCases.getSportsHeadlinesUseCase(page = page)
    }

    suspend fun getBusinessHeadlines(page: Int) : List<Article>{
        return newsUseCases.getBusinessHeadlinesUseCase(page = page)
    }

    suspend fun getScienceHeadlines(page: Int) : List<Article>{
        return newsUseCases.getScienceHeadlinesUseCase(page = page)
    }

    suspend fun getTechnologyHeadlines(page: Int) : List<Article>{
        return newsUseCases.getTechnologyHeadlinesUseCase(page = page)
    }

    suspend fun getHealthHeadlines(page: Int) : List<Article>{
        return newsUseCases.getHealthHeadlinesUseCase(page = page)
    }

    suspend fun getEntertainmentHeadlines(page: Int) : List<Article>{
        return newsUseCases.getEntertainmentHeadlinesUseCase(page = page)
    }

}