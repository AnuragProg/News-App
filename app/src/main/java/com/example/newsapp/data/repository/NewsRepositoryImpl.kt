package com.example.newsapp.data.repository

import com.example.newsapp.data.remotedatasource.NewsService
import com.example.newsapp.domain.model.NewsResponse
import com.example.newsapp.domain.repository.NewsRepository
import retrofit2.Response

class NewsRepositoryImpl(
    private val newsService: NewsService
): NewsRepository {


    override suspend fun getTopHeadlines(country: String, page: Int): Response<NewsResponse> {
        return newsService.getTopHeadlines(country = country, page = page)
    }

    override suspend fun getSearchedNews(query: String, page: Int): Response<NewsResponse> {
        return newsService.getSearchedNews(query = query, page = page)
    }

    override suspend fun getCategoryNews(category: String, page: Int): Response<NewsResponse> {
        return newsService.getCategoryNews(category = category, page = page)
    }


}