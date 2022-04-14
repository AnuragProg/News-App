package com.example.newsapp.presenter.navigationcomponents

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HealthAndSafety
import androidx.compose.material.icons.filled.MilitaryTech
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Science
import androidx.compose.ui.graphics.vector.ImageVector

enum class DestinationsExtended(
    val route: String,
    val icon : ImageVector,
    val label: String
) {
    ScienceScreen("ScienceScreen", Icons.Filled.Science, "Science"),
    HealthScreen("HealthScreen", Icons.Filled.HealthAndSafety, "Health"),
    EntertainmentScreen("EntertainmentScreen", Icons.Filled.Movie, "Entertainment")
}