package com.example.newsapp.presenter.screens.newsscreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun NewsScreen(
    url: String?
){

    if(url!=null){
        val state = rememberWebViewState(url = url)
        WebView(
            state = state,
            onCreated = {
                it.settings.javaScriptEnabled = true
            }
        )
    }else{
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Text("Not Able to Load News", fontSize = 30.sp)
        }
    }

}