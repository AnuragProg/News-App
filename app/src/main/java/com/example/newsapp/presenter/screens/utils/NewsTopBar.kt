package com.example.newsapp.presenter.screens.utils


import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable


@Composable
fun NewsTopBar(
    changeDrawerState: () -> Unit
){

    TopAppBar(
        title = {
                Text("News App")
        },
        navigationIcon = {
            IconButton( onClick = {
                changeDrawerState()
            } ) {
                Icon(
                    Icons.Filled.Menu,
                    contentDescription = "Menu"
                )
            }
        },
        actions = {

        }
    )

}