package com.example.newsapp.presenter.screens.homescreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.example.newsapp.domain.model.Article
import com.example.newsapp.presenter.navigationcomponents.Destinations
import com.example.newsapp.presenter.screens.utils.NewsCard
import com.example.newsapp.presenter.viewmodel.NewsViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.flow.map
import java.net.URLEncoder
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets

@ExperimentalMaterialApi
@Composable
fun HomeScreen(
    newsViewModel: NewsViewModel,
    navController: NavController
){
    val articlesFlow = newsViewModel.getPagingTopHeadlines()
    val articles = articlesFlow.collectAsLazyPagingItems()


    Log.d("debugging", "current destination ${navController.currentDestination?.route}")
    Log.d("debugging", "id ${navController.currentBackStackEntry?.id}")
    Log.d("debugging", "backstackentry ${navController.currentBackStackEntry}")


    Scaffold{
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn{
                items(articles.itemSnapshotList)  {
                    if(it!!.description != null){
                        NewsCard(navController = navController, it = it)
                    }
                }
            }
        }
    }
}