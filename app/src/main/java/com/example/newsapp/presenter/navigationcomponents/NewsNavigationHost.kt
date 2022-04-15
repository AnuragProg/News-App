package com.example.newsapp.presenter.navigationcomponents

import android.app.Activity
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.presenter.screens.nointernetscreen.NoInternetScreen
import com.example.newsapp.presenter.screens.businessscreen.BusinessScreen
import com.example.newsapp.presenter.screens.entertainment.EntertainmentScreen
import com.example.newsapp.presenter.screens.healthscreen.HealthScreen
import com.example.newsapp.presenter.screens.homescreen.HomeScreen
import com.example.newsapp.presenter.screens.sciencescreen.ScienceScreen
import com.example.newsapp.presenter.screens.searchscreen.SearchScreen
import com.example.newsapp.presenter.screens.splashscreen.SplashScreen
import com.example.newsapp.presenter.screens.sportsscreen.SportsScreen
import com.example.newsapp.presenter.screens.technologyscreen.TechnologyScreen
import com.example.newsapp.presenter.screens.utils.NetworkCheck
import com.example.newsapp.presenter.viewmodel.NewsViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NavigationHost(){



    val navController = rememberNavController()
    val context = LocalContext.current as Activity
    val lifecycleOwner = LocalLifecycleOwner.current
    var isInternetAvailable by remember{
        mutableStateOf(false)
    }
    
    DisposableEffect(key1 = lifecycleOwner){
        val observer = LifecycleEventObserver{ _, event ->
            if(event == Lifecycle.Event.ON_START){
                isInternetAvailable = NetworkCheck.isInternetAvailable(context)
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        
        onDispose { 
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
    
    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = navController,
        startDestination = Destinations.SplashScreen.route
    ){
        composable(Destinations.SplashScreen.route){
            SplashScreen(navController = navController)
        }
        composable(Destinations.HomeScreen.route){
            Log.d("debugging", "Entering homeScreen")
            if(isInternetAvailable){
                val newsViewModel = hiltViewModel<NewsViewModel>()
                HomeScreen(
                    context,
                    newsViewModel,
                    navController,
                ){ isInternetAvailable=it }
            }else{
                NoInternetScreen(
                    context,
                    navController
                ) { isInternetAvailable = true }
            }
            BackHandler(enabled = true) {
                context.finish()
            }
        }
        composable(Destinations.SearchScreen.route){
            if(isInternetAvailable){
                val newsViewModel = hiltViewModel<NewsViewModel>()
                SearchScreen(
                    context,
                    newsViewModel,
                    navController
                ){ isInternetAvailable = it }
            }else{
                NoInternetScreen(
                    context,
                    navController
                ){ isInternetAvailable = true }
            }
        }
        composable(Destinations.SportsScreen.route){
            if(isInternetAvailable){
                val newsViewModel = hiltViewModel<NewsViewModel>()
                SportsScreen(
                    context,
                    navController,
                    newsViewModel
                ){ isInternetAvailable = it }
            }else{
                NoInternetScreen(
                    context,
                    navController
                ){ isInternetAvailable = true }
            }
        }
        composable(Destinations.BusinessScreen.route){
            if(isInternetAvailable){
                val newsViewModel = hiltViewModel<NewsViewModel>()
                BusinessScreen(
                    context,
                    newsViewModel,
                    navController
                ){ isInternetAvailable = it }
            }else{
                NoInternetScreen(
                    context,
                    navController
                ){ isInternetAvailable = true }
            }
        }
        composable(DestinationsExtended.ScienceScreen.route){
            if(isInternetAvailable){
                val newsViewModel = hiltViewModel<NewsViewModel>()
                ScienceScreen(
                    context,
                    newsViewModel,
                    navController
                ){ isInternetAvailable = it }
            }else{
                NoInternetScreen(
                    context,
                    navController
                ){ isInternetAvailable = true }
            }
        }
        composable(Destinations.TechnologyScreen.route){
            if(isInternetAvailable){
                val newsViewModel = hiltViewModel<NewsViewModel>()
                TechnologyScreen(
                    context,
                    newsViewModel,
                    navController
                ){ isInternetAvailable = it }
            }else{
                NoInternetScreen(
                    context,
                    navController
                ){ isInternetAvailable = true }
            }
        }
        composable(DestinationsExtended.HealthScreen.route){
            if(isInternetAvailable){
                val newsViewModel = hiltViewModel<NewsViewModel>()
                HealthScreen(
                    context,
                    newsViewModel,
                    navController
                ){ isInternetAvailable = it }
            }else{
                NoInternetScreen(
                    context,
                    navController
                ){ isInternetAvailable = true }
            }
        }
        composable(DestinationsExtended.EntertainmentScreen.route){
            if(isInternetAvailable){
                val newsViewModel = hiltViewModel<NewsViewModel>()
                EntertainmentScreen(
                    context,
                    newsViewModel,
                    navController
                ){ isInternetAvailable = it }
            }else{
                NoInternetScreen(
                    context,
                    navController
                ){ isInternetAvailable = true }
            }
        }
    }
}


