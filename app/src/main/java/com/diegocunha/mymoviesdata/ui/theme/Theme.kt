package com.diegocunha.mymoviesdata.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.diegocunha.mymoviesdata.ui.theme.MovieTheme.material

@Composable
fun MovieAppTheme(
    colors: MovieColors = defaultColorsBySystem(),
    typography: MoviesTypography = MoviesTypography.default,
    content: @Composable() () -> Unit
) {

    CompositionLocalProvider(
        LocalPokeDexColors provides colors,
        LocalPokeDexTypography provides typography,
    ) {
        MaterialTheme(
            colors = MovieTheme.colors.material,
            typography = MovieTheme.typography.material,
            shapes = Shapes,
            content = content
        )
    }
}

@Composable
private fun defaultColorsBySystem(): MovieColors {
    return if (isSystemInDarkTheme()) {
        MovieColors.darkMode
    } else {
        MovieColors.lightMode
    }
}