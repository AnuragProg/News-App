package com.example.newsapp.presenter.screens.utils

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.newsapp.presenter.navigationcomponents.Destinations

@Composable
fun NewsBottomNavigation(
    navController: NavController
){

    val navigationItems = Destinations.values()

    val destination = navController.currentBackStackEntryAsState()

    BottomNavigation(
        backgroundColor = Color.White,
    ) {
        Destinations.values().filter{
            it.selectedIcon != null
        }.forEach{
            BottomNavigationItem(
                selected = destination.value?.destination?.route == it.route,
                onClick = {
                    navController.navigate(it.route)
                },
                icon = {
                    val icon = if(destination.value?.destination?.route == it.route) it.selectedIcon else it.unselectedIcon
                    Icon(
                        icon!!,
                        contentDescription = it.route
                    )
                }
            )
        }
    }

}