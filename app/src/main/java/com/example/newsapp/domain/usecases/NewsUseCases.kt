package com.example.newsapp.domain.usecases

data class NewsUseCases(
    val getTopHeadlinesUseCase: GetTopHeadlinesUseCase,
    val getSearchedNewsUseCase: GetSearchedNewsUseCase

)