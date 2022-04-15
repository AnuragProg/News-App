package com.example.newsapp.presenter.screens.nonewsscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.newsapp.R

@Composable
fun NoNewsScreen(){

    Scaffold() {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Column(){
                Image(
                    modifier = Modifier.size(150.dp, 150.dp)
                        .align(Alignment.CenterHorizontally),
                    painter = painterResource(id = R.drawable.no_news_available_image),
                    contentDescription = "No news available"
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Unable to load news",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}