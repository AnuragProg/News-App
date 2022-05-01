package com.example.newsapp.presenter.navigationcomponents

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.newsapp.R


@Composable
fun SearchFabButton(
    navController: NavController
){

    val currentBackStack = navController.currentBackStackEntryAsState()

    FloatingActionButton(onClick = {
        if(currentBackStack.value?.destination?.route != Destinations.SearchScreen.route){
            navController.navigate(Destinations.SearchScreen.route)
        }
    }
    ) {
        Icon(
            Icons.Filled.Search,
            contentDescription = stringResource(id = R.string.search)
        )
    }
}