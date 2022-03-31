package com.example.newsapp.presenter.navigationcomponents

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

enum class Destinations(val route: String, val icon: ImageVector?, val label: String?){
    HomeScreen("HomeScreen", Icons.Filled.Home, "Home"),
    NewsScreen("NewsScreen", null, null),
    SearchScreen("SearchScreen", Icons.Filled.Search, "Search")
}
