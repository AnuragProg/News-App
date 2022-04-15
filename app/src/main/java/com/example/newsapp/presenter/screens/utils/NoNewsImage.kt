package com.example.newsapp.presenter.screens.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.FixedScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.example.newsapp.R

@Composable
fun NoNewsImage(
    extraInfo: String?
){

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column(){
            Image(
                painter = painterResource(R.drawable.no_news_available_image),
                contentScale = FixedScale(0.5f),
                contentDescription = "No News Available"
            )
            if(extraInfo != null){
                Text(
                    text = extraInfo,
                    textAlign = TextAlign.Center
                )
            }
        }
    }

}