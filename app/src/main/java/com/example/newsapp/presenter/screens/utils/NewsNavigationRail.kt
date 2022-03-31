package com.example.newsapp.presenter.screens.utils

import android.util.Log
import androidx.compose.material.Icon
import androidx.compose.material.NavigationRail
import androidx.compose.material.NavigationRailItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.newsapp.presenter.navigationcomponents.Destinations


@Composable
fun NewsNavigationRail(
    navController: NavHostController
){

    val navigationItems = Destinations.values()

    NavigationRail{
        navigationItems.filter{
            it.icon != null && it.label != null
        }.forEach {
            NavigationRailItem(
                icon = { Icon(
                    it.icon!!,
                    contentDescription = it.name
                ) },
                selected = true,
                onClick = {
                    navController.navigate(it.route)
                },
                selectedContentColor = Color.Blue,
                label = { Text(it.label!!) }
            )
        }
    }

}
