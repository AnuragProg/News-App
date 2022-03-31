package com.example.newsapp.domain.usecases

import android.util.Log
import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.repository.NewsRepository

// DEPRECATED
class GetTopHeadlinesUseCase(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(country: String): List<Article>{
        val topHeadlines = newsRepository.getTopHeadlines(country,1)
        Log.d("debugging", "Top headlines in use case is ${topHeadlines.body()}")
        return if(topHeadlines.body() != null){
            topHeadlines.body()!!.articles
        }else{
            emptyList()
        }
    }
}