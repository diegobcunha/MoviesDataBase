package com.diegocunha.mymoviesdata.ui.components

import android.app.Activity
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import com.diegocunha.mymoviesdata.ui.theme.MovieTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

/**
 * TopAppBar used as project's default. This component differs from Compose's default because the
 * later uses the primaryColor as the backgroundColor, while this component uses the secondaryColor
 *
 * @param title - The title to be displayed in the center of the TopAppBar
 * @param modifier - The Modifier to be applied to this TopAppBar
 * @param navigationIcon - The navigation icon displayed at the start of the TopAppBar. This should
 * typically be an IconButton or IconToggleButton.
 * @param actions - The actions displayed at the end of the TopAppBar. This should typically be
 * IconButtons. The default layout here is a Row, so icons inside will be placed horizontally.
 * @param statusBar - Setup of status bar using accompanist library to do it. Be careful when
 * overriding this property because is important to use it wrapped by SideEffect
 * @param backgroundColor - The background color for the TopAppBar. Use Color.Transparent to have no
 * color.
 * @param contentColor - The preferred content color provided by this TopAppBar to its children.
 * Defaults to either the matching content color for backgroundColor, or if backgroundColor is not a
 * color from the theme, this will keep the same value set above this TopAppBar.
 * @param statusBarColor - Color used by statusBar param. Use systemBar if you need a more
 * sophisticated way to handle the status bar
 * @param elevation - the elevation of this TopAppBar.
 */
@Composable
fun DefaultTopAppBar(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
    backgroundColor: Color = MovieTheme.colors.base.primaryDarken,
    contentColor: Color = MovieTheme.colors.text.primary,
    statusBarColor: Color = MovieTheme.colors.base.secondary,
    statusBar: @Composable () -> Unit = { StatusBar(color = statusBarColor) },
    elevation: Dp = AppBarDefaults.TopAppBarElevation
) {
    statusBar()
    TopAppBar(
        title = title,
        modifier = modifier,
        navigationIcon = navigationIcon,
        actions = actions,
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        elevation = elevation
    )
}

/**
 * Composable function responsible by changing status bar color
 *
 * @param color Desired status bar color
 * @param darkIcons Whether dark status bar icons would be preferable
 */
@Composable
fun StatusBar(color: Color, darkIcons: Boolean = MaterialTheme.colors.isLight) {
    val uiController = rememberSystemUiController()
    SideEffect {
        uiController.setStatusBarColor(color, darkIcons)
    }
}

@Composable
fun BackNavigationIcon() {
    val activity = (LocalContext.current as Activity)
    IconButton(
        onClick = {
            activity.onBackPressed()
        }
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Voltar"
        )
    }
}