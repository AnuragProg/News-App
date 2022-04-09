package com.example.newsapp.presenter.navigationcomponents

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector

enum class Destinations(val route: String, val selectedIcon: ImageVector?,val unselectedIcon: ImageVector?, val label: String?){
    SplashScreen("SplashScreen", null, null, null),
    HomeScreen("HomeScreen", Icons.Filled.Home, Icons.Outlined.Home, "Home"),
    NewsScreen("NewsScreen", null, null, null),
    SearchScreen("SearchScreen", Icons.Filled.Search, Icons.Outlined.Search, "Search")
}
