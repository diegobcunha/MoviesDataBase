package com.diegocunha.mymoviesdata.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

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
        modifier = Modifier.padding(bottom = 16.dp),
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