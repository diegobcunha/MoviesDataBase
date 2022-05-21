package com.diegocunha.mymoviesdata.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

/**
 * A Circular Imager to be used at components
 * @param modifier Modifier which will be applied to image
 * @param painter painter to show image
 */
@Composable
fun CircularImage(
    modifier: Modifier = Modifier,
    painter: Painter
) {
    Image(
        modifier = modifier
            .size(64.dp)
            .clip(CircleShape),
        painter = painter, contentDescription = ""
    )
}