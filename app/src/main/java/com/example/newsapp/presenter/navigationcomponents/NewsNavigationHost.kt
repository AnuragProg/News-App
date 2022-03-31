package com.example.newsapp.presenter.navigationcomponents

import androidx.compose.foundation.layout.Row
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newsapp.presenter.navigationcomponents.Destinations
import com.example.newsapp.presenter.screens.homescreen.HomeScreen
import com.example.newsapp.presenter.screens.newsscreen.NewsScreen
import com.example.newsapp.presenter.screens.searchscreen.SearchScreen
import com.example.newsapp.presenter.screens.utils.NewsNavigationRail
import com.example.newsapp.presenter.viewmodel.NewsViewModel
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NavigationHost(){

    val navController = rememberNavController()

    Row {
        NewsNavigationRail(
            navController = navController
        )

        NavHost(navController = navController, startDestination = Destinations.HomeScreen.route){
            composable(Destinations.HomeScreen.route){
                val newsViewModel = hiltViewModel<NewsViewModel>()
                HomeScreen(newsViewModel, navController)
            }
            composable(
                "${Destinations.NewsScreen.route}/{url}",
                arguments = listOf(navArgument("url"){
                    type = NavType.StringType
                })
            ){
                val url = it.arguments?.getString("url")
                NewsScreen(url)
            }
            composable(Destinations.SearchScreen.route){
                val newsViewModel = hiltViewModel<NewsViewModel>()
                SearchScreen(newsViewModel = newsViewModel, navController = navController)
            }
        } 
    }
}