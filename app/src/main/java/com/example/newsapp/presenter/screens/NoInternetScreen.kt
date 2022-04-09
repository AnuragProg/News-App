package com.example.newsapp.presenter.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.newsapp.R
import com.example.newsapp.presenter.screens.utils.NetworkCheck
import com.example.newsapp.presenter.screens.utils.NewsBottomNavigation

@Composable
fun NoInternetScreen(
    context: Context,
    navController: NavController,
    isInternetAvailableStateChange: ()->Unit
){

    Scaffold(
        bottomBar = {
            NewsBottomNavigation(
                navController =
                navController
            )
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
                    modifier = Modifier.align(
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