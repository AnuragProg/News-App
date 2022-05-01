package com.example.newsapp.presenter.screens.technologyscreen

import android.content.Context
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
import com.example.newsapp.presenter.navigationcomponents.NewsBottomNavigation
import com.example.newsapp.presenter.navigationcomponents.NewsDrawerNavigation
import com.example.newsapp.presenter.navigationcomponents.SearchFabButton
import com.example.newsapp.presenter.screens.nonewsscreen.NoNewsScreen
import com.example.newsapp.presenter.screens.utils.NetworkCheck
import com.example.newsapp.presenter.screens.utils.NewsCard
import com.example.newsapp.presenter.screens.utils.NewsTopBar
import com.example.newsapp.presenter.viewmodel.NewsViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TechnologyScreen(
    context: Context,
    newsViewModel: NewsViewModel,
    navController: NavController,
    isInternetAvailableChange: (Boolean) -> Unit,
){

    val scaffoldState = rememberScaffoldState()

    val coroutineScope = rememberCoroutineScope()

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
            val articleResponse = newsViewModel.getTechnologyHeadlines(page = pageToLoad)
            articles.addAll(articleResponse.filter{ it.title != null })
            isInitialLoadDone = true
        }
    }

    LaunchedEffect(shouldLoadMoreHeadlines){
        if(shouldLoadMoreHeadlines){
            val articleResponse = newsViewModel.getTechnologyHeadlines(page = ++pageToLoad)
            articles.addAll(articleResponse.filter{ it.title!=null })
            shouldLoadMoreHeadlines = false
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = { NewsBottomNavigation(navController = navController) },
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
                NoNewsScreen()
            } else if (!isInitialLoadDone && articles.isEmpty() && NetworkCheck.isInternetAvailable(context)){
                CircularProgressIndicator()
            } else if (articles.isNotEmpty()) {
                LazyColumn {
                    itemsIndexed(articles) { index, item ->
                        if (index == articles.size - 1 && articles.size != 0 && !shouldLoadMoreHeadlines && NetworkCheck.isInternetAvailable(context)) {
                            shouldLoadMoreHeadlines = true
                        }
                        else{
                            NewsCard(
                                item
                            )
                        }
                    }
                }
            }
        }
    }
}