package com.example.newsapp.presenter.screens.splashscreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.example.newsapp.R
import com.example.newsapp.presenter.navigationcomponents.Destinations
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController
){
    var imageVisibility by remember{
        mutableStateOf(false)
    }

    LaunchedEffect(Unit){
        delay(1000)
        imageVisibility = true
        delay(8000)
        navController.navigate(Destinations.HomeScreen.route)
    }


    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column(){
            AnimatedVisibility(
                visible = imageVisibility,
                enter = fadeIn(
                    TweenSpec(
                        durationMillis = 3000
                    )
                ),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.news_splash_screen),
                    contentDescription = "News Splash Screen"
                )
            }
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Read News Everyday",
                textAlign = TextAlign.Center
            )
        }
    }
}

