package com.example.newsapp.presenter.screens.utils

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import com.example.newsapp.domain.model.Article
import com.example.newsapp.presenter.navigationcomponents.Destinations
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@ExperimentalMaterialApi
@Composable
fun NewsCard(
    navController: NavController,
    it: Article
){
    Card(
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
            .padding(10.dp),
        elevation = 10.dp,
        onClick = {
            val url =
                URLEncoder.encode(it.url, StandardCharsets.UTF_8.toString())
            Log.d("deubgging", "Url is $url")
            navController.navigate("${Destinations.NewsScreen.route}/$url")
        }
    ) {

        SubcomposeAsyncImage(
            model = it.urlToImage,
            contentDescription = "News Image",
            loading = {
                CircularProgressIndicator(
                    strokeWidth = 5.dp
                )
            },
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black
                        )
                    )
                ),
            contentAlignment = Alignment.BottomCenter
        ) {
            Text(
                modifier = Modifier
                    .padding(5.dp),
                text = it.description,
                fontSize = 15.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = Color.White
            )
        }
    }
}