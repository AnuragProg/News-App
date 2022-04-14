package com.example.newsapp.presenter.screens.newsscreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.newsapp.presenter.navigationcomponents.NewsBottomNavigation
import com.example.newsapp.presenter.navigationcomponents.NewsDrawerNavigation
import com.example.newsapp.presenter.navigationcomponents.SearchFabButton
import com.example.newsapp.presenter.screens.utils.NewsTopBar
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState
import kotlinx.coroutines.launch

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun NewsScreen(
    url: String?,
    navController: NavController,
){

    val scaffoldState = rememberScaffoldState()

    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            NewsBottomNavigation(navController = navController)
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
        if(url!=null){
            val state = rememberWebViewState(url = url)
            WebView(
                modifier = Modifier.padding(it),
                state = state,
                onCreated = {
                    it.settings.javaScriptEnabled = false
                }
            )
        }else{
            Box(
                modifier = Modifier
                    .padding(it),
                contentAlignment = Alignment.Center
            ){
                Text("Not Able to Load News", fontSize = 30.sp)
            }
        }
    }

}