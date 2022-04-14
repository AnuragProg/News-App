package com.example.newsapp.presenter.navigationcomponents

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.vector.ImageVector

enum class Destinations(val route: String, val selectedIcon: ImageVector?,val unselectedIcon: ImageVector?, val label: String?){
    SplashScreen("SplashScreen", null, null, null),
    HomeScreen("HomeScreen", Icons.Filled.Home, Icons.Outlined.Home, "Home"),
    NewsScreen("NewsScreen", null, null, null),
    TechnologyScreen("TechnologyScreen", Icons.Filled.MilitaryTech, Icons.Outlined.MilitaryTech,"Technology"),
    SearchScreen("SearchScreen", null, null, "Search"),
    SportsScreen("SportsScreen", Icons.Filled.Sports, Icons.Outlined.Sports, "Sports"),
    BusinessScreen("BusinessScreen", Icons.Filled.Business, Icons.Filled.Business, "Business")
}
