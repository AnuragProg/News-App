package com.example.newsapp.domain.usecases

import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.repository.NewsRepository
import java.net.SocketTimeoutException

class GetScienceHeadlinesUseCase(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(category: String = "science", page: Int): List<Article>{
        return try{
            val articleResponse = newsRepository.getCategoryNews(category, page)
            val articles = articleResponse.body()?.articles ?: emptyList()
            articles
        }catch(e: SocketTimeoutException){
            emptyList()
        }
    }

}