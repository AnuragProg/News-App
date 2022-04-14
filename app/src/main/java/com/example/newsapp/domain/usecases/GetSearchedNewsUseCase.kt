package com.example.newsapp.domain.usecases

import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.repository.NewsRepository
import java.net.SocketTimeoutException

class GetSearchedNewsUseCase(
    private val newsRepository: NewsRepository
){

    suspend operator fun invoke(query: String, page: Int = 1): List<Article> {
        return try{
            val articleResponse = newsRepository.getSearchedNews(query = query, page = page)
            val articles = articleResponse.body()?.articles ?: emptyList()
            articles
        }catch(e: SocketTimeoutException){
            emptyList()
        }
    }
}