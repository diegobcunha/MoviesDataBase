package com.diegocunha.mymoviesdata.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridItemSpanScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.diegocunha.mymoviesdata.ui.theme.MovieTheme

/**
 * Notify the user that something is loading with a circular loading
 * @param modifier Modifier to be applied to the LoadingCircular
 * @param color color to be applied to the LoadingCircular
 * @param strokeWidth the stroke width of the LoadingCircular
 */
@Composable
fun LoadingCircular(
    modifier: Modifier = Modifier,
    color: Color = MovieTheme.colors.element.primary,
    strokeWidth: Dp = ProgressIndicatorDefaults.StrokeWidth
) {
    CircularProgressIndicator(
        modifier = modifier.size(24.dp),
        color = color,
        strokeWidth = strokeWidth
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LazyGridItemScope.renderLoading(
    modifier: Modifier = Modifier,
    loadState: CombinedLoadStates
) {
    if (loadState.append !is LoadState.Loading) return

    LoadingCircular(modifier = modifier)
}