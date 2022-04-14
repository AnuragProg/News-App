package com.example.newsapp.presenter.screens.nointernetscreen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.newsapp.R
import com.example.newsapp.presenter.screens.utils.NetworkCheck
import com.example.newsapp.presenter.navigationcomponents.NewsBottomNavigation
import com.example.newsapp.presenter.navigationcomponents.NewsDrawerNavigation
import com.example.newsapp.presenter.navigationcomponents.SearchFabButton
import com.example.newsapp.presenter.screens.utils.NewsTopBar
import kotlinx.coroutines.launch

@Composable
fun NoInternetScreen(
    context: Context,
    navController: NavController,
    isInternetAvailableStateChange: ()->Unit
){

    val scaffoldState = rememberScaffoldState()

    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
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
        }},
        floatingActionButton = {
            SearchFabButton(navController = navController)
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        bottomBar = {
            NewsBottomNavigation(navController = navController)
        },
        drawerContent = {
            NewsDrawerNavigation(navController = navController)
        }
    ){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentAlignment = Alignment.Center
        ){
            Column{
                Image(
                    modifier = Modifier.size(150.dp, 150.dp)
                        .align(
                        Alignment.CenterHorizontally
                    ),
                    painter = painterResource(id = R.drawable.no_internet),
                    contentDescription = "No internet"
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Please Connect to Internet",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedButton(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    onClick = {
                        if(NetworkCheck.isInternetAvailable(context)){
                            isInternetAvailableStateChange()
                        }else{
                            Toast.makeText(context, "Internet not available", Toast.LENGTH_SHORT).show()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
                    ){
                    Text("Retry", color = Color.Blue)
                }
            }
        }
    }
}