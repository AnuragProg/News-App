package com.example.newsapp.data.remotedatasource

import com.example.newsapp.data.utils.NewsServiceUtils.APIKEY
import com.example.newsapp.data.utils.NewsServiceUtils.EVERYTHING
import com.example.newsapp.data.utils.NewsServiceUtils.TOPHEADLINES
import com.example.newsapp.domain.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsService {

    @GET(TOPHEADLINES)
    suspend fun getTopHeadlines(
        @Query("country") country: String,
        @Query("apiKey") apikey: String = APIKEY,
        @Query("page") page: Int = 1
    ) : Response<NewsResponse>


    @GET(EVERYTHING)
    suspend fun getSearchedNews(
        @Query("q") query: String,
        @Query("apiKey") apikey: String = APIKEY,
        @Query("page") page: Int = 1,
        @Query("language") language: String = "en"
    ) : Response<NewsResponse>

    @GET(TOPHEADLINES)
    suspend fun getCategoryNews(
        @Query("category") category: String,
        @Query("apiKey") apikey: String = APIKEY,
        @Query("page") page: Int = 1,
        @Query("language") language: String = "en"
    ) : Response<NewsResponse>
}