package com.example.newsapp.presenter.screens.homescreen

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.FixedScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.newsapp.R
import com.example.newsapp.domain.model.Article
import com.example.newsapp.presenter.screens.utils.NetworkCheck
import com.example.newsapp.presenter.screens.utils.NewsBottomNavigation
import com.example.newsapp.presenter.screens.utils.NewsCard
import com.example.newsapp.presenter.screens.utils.NoNewsImage
import com.example.newsapp.presenter.viewmodel.NewsViewModel

@ExperimentalMaterialApi
@Composable
fun HomeScreen(
    context: Context,
    newsViewModel: NewsViewModel,
    navController: NavController,
    isInternetAvailableChange: (Boolean) -> Unit,
){
    val articles = remember{
        mutableStateListOf<Article>()
    }

    var shouldLoadMoreHeadlines by remember{
        mutableStateOf(false)
    }

    var pageToLoad by remember{
        mutableStateOf(1)
    }

    var isInitialLoadDone by remember{
        mutableStateOf(false)
    }

    LaunchedEffect(Unit){
        if(!NetworkCheck.isInternetAvailable(context)){
            isInternetAvailableChange(false)
        }else{
            Log.d("debugging", "Starting to fetch headlines from api")
            val articleResponse = newsViewModel.getTopHeadlines("in", pageToLoad)
            Log.d("debugging", "Article Response from initial launch is $articleResponse")
            articles.addAll(articleResponse)
            isInitialLoadDone = true
        }
    }

    LaunchedEffect(shouldLoadMoreHeadlines){
        if(shouldLoadMoreHeadlines){
            val articleResponse = newsViewModel.getTopHeadlines("in", ++pageToLoad)
            articles.addAll(articleResponse)
            shouldLoadMoreHeadlines = false
        }
    }

    Scaffold(
        bottomBar = { NewsBottomNavigation(navController = navController)}
    ){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentAlignment = Alignment.Center
        ) {
            if (!NetworkCheck.isInternetAvailable(context)) {
                isInternetAvailableChange(false)
            } else if (isInitialLoadDone && articles.isEmpty() && NetworkCheck.isInternetAvailable(context)) {
                Column{
                    Image(
                        modifier = Modifier.padding(
                            start = 25.dp,
                            top = 15.dp,
                            end = 25.dp
                        ),
                        contentScale = FixedScale(0.6f),
                        painter = painterResource(id = R.drawable.news_load_fail_image),
                        contentDescription = "Loading Failed"
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Unable To Load News",
                        textAlign = TextAlign.Center
                    )
                }
            } else if (!isInitialLoadDone && articles.isEmpty() && NetworkCheck.isInternetAvailable(context)){
                CircularProgressIndicator()
            } else if (articles.isNotEmpty()) {
                LazyColumn {
                    itemsIndexed(articles) { index, item ->
                        // check if we reached at the end of non empty article list
                        // check also that there is no other same loading taking place
                        if (index == articles.size - 1 && articles.size != 0 && !shouldLoadMoreHeadlines && NetworkCheck.isInternetAvailable(context)) {
                            shouldLoadMoreHeadlines = true
                        }
                        if (item.description != null && item.description.isNotBlank()) {
                            NewsCard(
                                navController,
                                item
                            )
                        }
                    }
                }
            }
        }
    }
}