package com.example.newsapp.presenter.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.usecases.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
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


}