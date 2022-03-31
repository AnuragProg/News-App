package com.example.newsapp.domain.usecases

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.repository.NewsRepository
import kotlin.properties.Delegates

class GetPagingSearchedNewsUseCase(
    private val newsRepository: NewsRepository
) : PagingSource<Int, Article>() {

    lateinit var query: String

    operator fun invoke(user_query: String){
        query = user_query
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try{
            val nextPage = params.key ?: 1
            val response = newsRepository.getSearchedNews(query = query, page = nextPage)
            Log.d("debugging", "response is $response")
            LoadResult.Page(
                prevKey = if(nextPage==1) null else nextPage-1,
                nextKey = nextPage.plus(1),
                data = response.body()?.articles ?: emptyList()
            )
        }catch(e: Exception){
            LoadResult.Error(e)
        }
    }


}