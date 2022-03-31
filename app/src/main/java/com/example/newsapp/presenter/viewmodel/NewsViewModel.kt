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
import kotlinx.coroutines.flow.filter
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
): ViewModel() {


    var searchQuery : String = ""

    var previousQuery: String = ""

    suspend fun getSearchedNews(query: String, page: Int) : List<Article>{
        return newsUseCases.getSearchedNewsUseCase(query, page)
    }

    fun getPagingTopHeadlines(country: String = "in") = Pager(
        PagingConfig(
            pageSize=20
        )
    ){
        Log.d("debugging", "inside getpagingtopheadlines viewmodel function")
        newsUseCases.getPagingTopHeadlinesUseCase(nation = country)
        newsUseCases.getPagingTopHeadlinesUseCase
    }.flow

    fun getPagingSearchedNews(query: String = searchQuery) = Pager(
        PagingConfig(
            pageSize = 20
        )
    ){
        Log.d("debugging", "inside getpagingsearchednews viewmodel function")
        newsUseCases.getPagingSearchedNewsUseCase(user_query = query)
        newsUseCases.getPagingSearchedNewsUseCase
    }.flow


}