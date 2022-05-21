package com.diegocunha.mymoviesdata.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.diegocunha.mymoviesdata.ui.theme.MovieTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CardBox(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
    onClick: () -> Unit
) {
    Box(modifier = modifier) {
        Card(
            modifier = Modifier
                .background(MovieTheme.colors.base.primaryDarken)
                .fillMaxSize(),
            shape = RoundedCornerShape(size = 8.dp),
            elevation = 8.dp,
            onClick = onClick
        ) {
            content()
        }
    }
}