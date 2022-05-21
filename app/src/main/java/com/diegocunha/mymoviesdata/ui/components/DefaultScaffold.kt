package com.diegocunha.mymoviesdata.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * A default Scaffold View to be used in screens.
 * @param title: Composable function to show Text Information at Toolbar
 * @param body: Composable function with body of the screen
 */
@Composable
fun DefaultScaffoldTopBar(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    title: @Composable () -> Unit,
    body: @Composable () -> Unit,
    navigationIcon: (@Composable () -> Unit)? = null
) {
    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize(),
        topBar = {
            DefaultTopAppBar(
                navigationIcon = navigationIcon,
                title = {
                    title()
                }
            )
        }
    ) { body() }
}