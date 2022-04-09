package com.example.newsapp.presenter.screens.searchscreen

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.newsapp.domain.model.Article
import com.example.newsapp.presenter.screens.utils.NetworkCheck
import com.example.newsapp.presenter.screens.utils.NewsBottomNavigation
import com.example.newsapp.presenter.screens.utils.NewsCard
import com.example.newsapp.presenter.viewmodel.NewsViewModel


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SearchScreen(
    context: Context,
    newsViewModel: NewsViewModel,
    navController: NavController,
    isInternetAvailableChange: (Boolean) -> Unit,
) {
    var query by remember{
        mutableStateOf("")
    }

    var updatedQuery by remember{
        mutableStateOf("")
    }

    val articles = remember{
        mutableStateListOf<Article>()
    }

    var shouldLoadPagingData by remember{
        mutableStateOf(false)
    }

    var pageToLoad by remember{
        mutableStateOf(1)
    }


    LaunchedEffect(updatedQuery){
        Log.d("debugging", "Inside updatedquery launched effect")
        if(updatedQuery.isNotBlank() && NetworkCheck.isInternetAvailable(context)){
            val articleResponse = newsViewModel.getSearchedNews(updatedQuery, pageToLoad)
            articles.clear()
            articles.addAll(articleResponse)
        }else if(!NetworkCheck.isInternetAvailable(context)){
            isInternetAvailableChange(false)
        }
    }

    LaunchedEffect(shouldLoadPagingData){
        Log.d("debugging", "inside should load paging data launched effect")
        if(shouldLoadPagingData && NetworkCheck.isInternetAvailable(context)){
            Log.d("debugging", "Started loading more data")
            val articleResponse = newsViewModel.getSearchedNews(newsViewModel.searchQuery, ++pageToLoad)
            articles.addAll(articleResponse)
            shouldLoadPagingData = false
        }
    }

    Scaffold(
        bottomBar = {
            NewsBottomNavigation(navController = navController)
        }
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                value = query,
                onValueChange = {query=it},
                singleLine = true,
                trailingIcon = {
                    IconButton(onClick = {
                        if(query.isNotBlank() && NetworkCheck.isInternetAvailable(context)){
                            updatedQuery = query
                            newsViewModel.searchQuery = updatedQuery
                            pageToLoad = 1
                        }else if(!NetworkCheck.isInternetAvailable(context)){
                            Toast.makeText(context, "Internet not available", Toast.LENGTH_SHORT).show()
                        }
                    }) {
                        Icon(
                            Icons.Filled.Search,
                            contentDescription = "Search"
                        )
                    }
                }
            )
            
            LazyColumn{
                itemsIndexed(articles){ index , item->

                    if(index == articles.size - 1 && articles.size != 0 && !shouldLoadPagingData && NetworkCheck.isInternetAvailable(context)){
                        shouldLoadPagingData = true
                    }
                    if(item.description != null && item.description.isNotBlank()){
                        NewsCard(
                            navController = navController,
                            it = item
                        )
                    }
                }
            }
        }
    }
}
