package com.example.newsapp.domain.usecases

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.repository.NewsRepository

class GetPagingTopHeadlinesUseCase(
    private val newsRepository: NewsRepository
) : PagingSource<Int, Article>() {

    lateinit var country: String

    operator fun invoke(nation: String){
        country = nation
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try{
            val nextPage = params.key ?: 1
            val response = newsRepository.getTopHeadlines(country, nextPage)
            val articles = response.body()?.articles ?: emptyList()
            Log.d("debugging", "found articles in use case $articles")
            LoadResult.Page(
                data = articles,
                prevKey = if(nextPage==1) null else nextPage,
                nextKey = nextPage.plus(1)
            )
        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }

}