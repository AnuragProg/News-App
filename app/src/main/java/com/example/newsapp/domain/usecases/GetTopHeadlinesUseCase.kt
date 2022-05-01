package com.example.newsapp.domain.usecases

import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.repository.NewsRepository
import java.net.SocketTimeoutException

class GetTopHeadlinesUseCase(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(country: String, page: Int): List<Article>{
        return try{
            val articleResponse = newsRepository.getTopHeadlines(country, page)
            val articles = articleResponse.body()?.articles ?: emptyList()
            articles
        }catch(e: SocketTimeoutException){
            emptyList()
        }

    }
}