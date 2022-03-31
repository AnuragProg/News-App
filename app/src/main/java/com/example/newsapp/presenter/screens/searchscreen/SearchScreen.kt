package com.example.newsapp.presenter.screens.searchscreen

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.newsapp.domain.model.Article
import com.example.newsapp.presenter.screens.utils.NewsCard
import com.example.newsapp.presenter.viewmodel.NewsViewModel


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SearchScreen(
    newsViewModel: NewsViewModel,
    navController: NavController
) {
    var query by remember{
        mutableStateOf("")
    }

    var updatedQuery by remember{
        mutableStateOf("")
    }

    var pageToLoad by remember{
        mutableStateOf(1)
    }
    
    val articles = remember{
        mutableStateListOf<Article>()
    }

    val lazyColumnState = rememberLazyListState()


    
    LaunchedEffect(key1 = updatedQuery, key2 = lazyColumnState.layoutInfo.visibleItemsInfo.lastOrNull()?.index){
        if(lazyColumnState.layoutInfo.visibleItemsInfo.lastOrNull() == null){
            Log.d("debugging", "Loading First time data")
            pageToLoad = 1
            val articleResponse = newsViewModel.getSearchedNews(query = updatedQuery, page = pageToLoad)
            articles.clear()
            articles.addAll(articleResponse)
        }
        else if(lazyColumnState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == lazyColumnState.layoutInfo.totalItemsCount -1 ){
            Log.d("debugging", "Loading more paging data")
            val articleResponse = newsViewModel.getSearchedNews(query = updatedQuery, page = ++pageToLoad)
            articles.addAll(articleResponse)
        }
    }
    
    
    Scaffold {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                value = query,
                onValueChange = {query=it},
                trailingIcon = {
                    IconButton(onClick = {
                        updatedQuery = query
                    }) {
                        Icon(
                            Icons.Filled.Search,
                            contentDescription = "Search"
                        )
                    }
                }
            )
            
            LazyColumn{
                items(articles){ 
                    if(it.description != null){
                        NewsCard(
                            navController = navController,
                            it = it
                        )
                    }
                }
            }
        }
    }
}
