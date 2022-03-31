package com.example.newsapp.domain.usecases

import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.repository.NewsRepository

class GetSearchedNewsUseCase(
    private val newsRepository: NewsRepository
){

    suspend operator fun invoke(query: String, page: Int = 1): List<Article>{
        val response = newsRepository.getSearchedNews(query = query, page = page)
        val articles = response.body()?.articles ?: emptyList()
        return articles.filter{
            it.description != null || it.urlToImage != null
        }
    }
}