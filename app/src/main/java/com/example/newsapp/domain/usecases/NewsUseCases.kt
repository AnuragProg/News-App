package com.example.newsapp.domain.usecases

data class NewsUseCases(
    val getTopHeadlinesUseCase: GetTopHeadlinesUseCase,
    val getPagingTopHeadlinesUseCase: GetPagingTopHeadlinesUseCase,
    val getPagingSearchedNewsUseCase: GetPagingSearchedNewsUseCase,
    val getSearchedNewsUseCase: GetSearchedNewsUseCase

)