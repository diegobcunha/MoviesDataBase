package com.diegocunha.mymoviesdata.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter

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

@Composable
fun ImageLoader(
    modifier: Modifier = Modifier,
    painter: AsyncImagePainter,
    contentDescription: String?
) {
    val scale = if (painter.state !is AsyncImagePainter.State.Success) {
        ContentScale.Fit
    } else {
        ContentScale.FillBounds
    }

    Image(
        modifier = modifier
            .fillMaxSize(),
        painter = painter,
        contentDescription = contentDescription,
        contentScale = scale,
    )
}