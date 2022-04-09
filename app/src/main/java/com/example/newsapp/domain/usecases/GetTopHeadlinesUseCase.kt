package com.example.newsapp.domain.usecases

import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.repository.NewsRepository
import okio.Timeout
import java.net.SocketTimeoutException

class GetTopHeadlinesUseCase(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(country: String, page: Int): List<Article>{
        val topHeadlines = newsRepository.getTopHeadlines(country,page)
        return if(topHeadlines.body() != null){
            topHeadlines.body()!!.articles
        }else{
            emptyList()
        }

    }
}