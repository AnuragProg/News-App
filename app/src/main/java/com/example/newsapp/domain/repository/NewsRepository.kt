package com.example.newsapp.domain.repository

import com.example.newsapp.domain.model.NewsResponse
import retrofit2.Response


interface NewsRepository {

    suspend fun getTopHeadlines(country: String, page: Int): Response<NewsResponse>

    suspend fun getSearchedNews(query: String, page: Int): Response<NewsResponse>

}