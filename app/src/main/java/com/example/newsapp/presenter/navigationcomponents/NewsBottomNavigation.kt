package com.example.newsapp.presenter.navigationcomponents

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun NewsBottomNavigation(
    navController: NavController
){

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
                    if(destination.value?.destination?.route != it.route){
                        navController.navigate(it.route)
                    }
                },
                icon = {
                    val icon = if(destination.value?.destination?.route == it.route) it.selectedIcon else it.unselectedIcon
                    Icon(
                        imageVector = icon!!,
                        contentDescription = it.route,
                    )
                },
                label = { Text(it.label.toString()) }
            )
        }
    }

}