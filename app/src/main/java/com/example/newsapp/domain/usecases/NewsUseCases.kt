package com.example.newsapp.domain.usecases

data class NewsUseCases(
    val getTopHeadlinesUseCase: GetTopHeadlinesUseCase,
    val getSearchedNewsUseCase: GetSearchedNewsUseCase,
    val getSportsHeadlinesUseCase: GetSportsHeadlinesUseCase,
    val getBusinessHeadlinesUseCase: GetBusinessHeadlinesUseCase,
    val getScienceHeadlinesUseCase: GetScienceHeadlinesUseCase,
    val getTechnologyHeadlinesUseCase: GetTechnologyHeadlinesUseCase,
    val getHealthHeadlinesUseCase: GetHealthHeadlinesUseCase,
    val getEntertainmentHeadlinesUseCase: GetEntertainmentHeadlinesUseCase
)