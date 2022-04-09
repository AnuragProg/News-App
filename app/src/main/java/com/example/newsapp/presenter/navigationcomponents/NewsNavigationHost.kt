package com.example.newsapp.presenter.navigationcomponents

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newsapp.presenter.screens.NoInternetScreen
import com.example.newsapp.presenter.screens.homescreen.HomeScreen
import com.example.newsapp.presenter.screens.newsscreen.NewsScreen
import com.example.newsapp.presenter.screens.searchscreen.SearchScreen
import com.example.newsapp.presenter.screens.splashscreen.SplashScreen
import com.example.newsapp.presenter.screens.utils.NetworkCheck
import com.example.newsapp.presenter.screens.utils.NewsBottomNavigation
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
                Log.d("debugging", "")
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
                    navController
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
        composable(
            "${Destinations.NewsScreen.route}/{url}",
            arguments = listOf(navArgument("url"){
                type = NavType.StringType
            })
        ){
            val url = it.arguments?.getString("url")
            NewsScreen(
                url,
                navController
            )
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
    }
}


