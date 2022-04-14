package com.example.newsapp.presenter.screens.sportsscreen

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.newsapp.domain.model.Article
import com.example.newsapp.presenter.screens.nonewsscreen.NoNewsScreen
import com.example.newsapp.presenter.screens.utils.NetworkCheck
import com.example.newsapp.presenter.navigationcomponents.NewsBottomNavigation
import com.example.newsapp.presenter.navigationcomponents.NewsDrawerNavigation
import com.example.newsapp.presenter.navigationcomponents.SearchFabButton
import com.example.newsapp.presenter.screens.utils.NewsCard
import com.example.newsapp.presenter.screens.utils.NewsTopBar
import com.example.newsapp.presenter.viewmodel.NewsViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SportsScreen(
    context: Context,
    navController: NavController,
    newsViewModel: NewsViewModel,
    isInternetAvailableChange: (Boolean) -> Unit
){

    val scaffoldState = rememberScaffoldState()

    val coroutineScope = rememberCoroutineScope()

    var pageToLoad by remember{
        mutableStateOf(1)
    }

    val articles = remember{
        mutableStateListOf<Article>()
    }

    var isInitialLoadDone by remember{
        mutableStateOf(false)
    }

    var shouldLoadMoreData by remember{
        mutableStateOf(false)
    }

    LaunchedEffect(Unit){
        if(NetworkCheck.isInternetAvailable(context)){
            Log.d("debugging", "Loading initial sports news")
            val articleResponse = newsViewModel.getSportsHeadlines(pageToLoad++)
            articles.addAll(articleResponse)
            isInitialLoadDone = true
        }else{
            isInternetAvailableChange(false)
        }
    }

    LaunchedEffect(shouldLoadMoreData){
        if(shouldLoadMoreData){
            Log.d("debugging", "Loading more sports news data")
            val articleResponse = newsViewModel.getSportsHeadlines(pageToLoad++)
            articles.addAll(articleResponse)
            shouldLoadMoreData = false
        }
    }

    
    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            NewsBottomNavigation(
                navController = navController
            )
        },
        topBar = { NewsTopBar {
            if(scaffoldState.drawerState.isOpen){
                coroutineScope.launch{
                    scaffoldState.drawerState.close()
                }
            }else{
                coroutineScope.launch{
                    scaffoldState.drawerState.open()
                }
            }
        }
        },
        floatingActionButton = {
            SearchFabButton(navController = navController)
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        drawerContent = {
            NewsDrawerNavigation(navController = navController)
        }
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentAlignment = Alignment.Center
        ){
            if(!NetworkCheck.isInternetAvailable(context)){
                isInternetAvailableChange(false)
            }else if(!isInitialLoadDone && articles.isEmpty() && NetworkCheck.isInternetAvailable(context)){
                CircularProgressIndicator()
            }else if(isInitialLoadDone && articles.isEmpty()){
                NoNewsScreen(navController)
            }else{
                LazyColumn(){
                    itemsIndexed(articles.filter{it.title !=null && it.title.isNotBlank()}){ index, it ->
                        if(index == articles.size -1 && articles.size!=0 && !shouldLoadMoreData && NetworkCheck.isInternetAvailable(context)){
                            shouldLoadMoreData = true
                        }else{
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
}