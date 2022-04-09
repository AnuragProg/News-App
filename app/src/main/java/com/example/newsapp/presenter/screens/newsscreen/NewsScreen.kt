package com.example.newsapp.presenter.screens.newsscreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.newsapp.presenter.screens.utils.NewsBottomNavigation
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun NewsScreen(
    url: String?,
    navController: NavController,
){

    Scaffold(
        bottomBar = {
            NewsBottomNavigation(navController = navController)
        }
    ) {
        if(url!=null){
            val state = rememberWebViewState(url = url)
            WebView(
                modifier = Modifier.padding(it),
                state = state,
                onCreated = {
                    it.settings.javaScriptEnabled = false
                }
            )
        }else{
            Box(
                modifier = Modifier
                    .padding(it),
                contentAlignment = Alignment.Center
            ){
                Text("Not Able to Load News", fontSize = 30.sp)
            }
        }
    }

}