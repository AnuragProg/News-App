package com.example.newsapp.presenter.screens.utils


import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.newsapp.R


@Composable
fun NewsTopBar(
    changeDrawerState: () -> Unit
){

    TopAppBar(
        title = {
                Text(stringResource(id = R.string.app_name))
        },
        navigationIcon = {
            IconButton( onClick = {
                changeDrawerState()
            } ) {
                Icon(
                    Icons.Filled.Menu,
                    contentDescription = stringResource(id = R.string.menu)
                )
            }
        },
        actions = {

        }
    )

}