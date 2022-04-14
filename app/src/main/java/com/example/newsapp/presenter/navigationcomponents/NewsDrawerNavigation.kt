
package com.example.newsapp.presenter.navigationcomponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.newsapp.R

@Composable
fun NewsDrawerNavigation(
    navController : NavController
){

    val newsDrawerSlotDestinations = DestinationsExtended.values()

    val currentBackStack = navController.currentBackStackEntryAsState()



    Column(){

        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            painter = painterResource(id = R.drawable.news_drawer_image),
            contentDescription = "Drawer Image",
            contentScale = ContentScale.FillBounds
        )
        Spacer(modifier = Modifier.height(5.dp))
        newsDrawerSlotDestinations.forEach{
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .clickable {
                        if (currentBackStack.value?.destination?.route != it.route) {
                            navController.navigate(it.route) {
                                launchSingleTop = true
                                popUpTo(it.route) {
                                    inclusive = true
                                }
                            }
                        }
                    }
            ){
                Icon(
                    it.icon,
                    contentDescription = it.label
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(it.label)
            }
        }
    }

}