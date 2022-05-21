package com.diegocunha.mymoviesdata.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.diegocunha.mymoviesdata.ui.theme.MovieTheme


/**
 * A Divider displays a default styled divider used as items separator.
 *
 * @param modifier - The modifier to be applied to the Divider.
 * @param color - Default color of divider
 */
@Composable
fun Divider(
    modifier: Modifier = Modifier,
    color: Color = MovieTheme.colors.base.secondaryHover,
) {
    androidx.compose.material.Divider(
        modifier = modifier,
        color = color,
        thickness = 16.dp
    )
}