package com.diegocunha.mymoviesdata.ui.components

import androidx.annotation.FloatRange
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.diegocunha.mymoviesdata.ui.theme.MovieTheme
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer

private const val DEFAULT_PLACEHOLDER_ROWS = 20

/**
 * Show an entire list of rows placeholder
 */
@Composable
fun PlaceholderFullScreen(modifier: Modifier = Modifier, placeholder: @Composable () -> Unit) {
    Column(modifier = modifier) {
        for (index in 0..DEFAULT_PLACEHOLDER_ROWS) {
            if (index > 0) {
                Divider()
            }
            placeholder()
        }
    }
}

/**
 * A simple placeholder of row that uses a TwoLine to align a text one above the other
 */
@Composable
@Preview
fun TwoLineRowListPlaceholder() {
    RegularRow(startContent = {
        TwoLine(
            primary = {
                Spacer(
                    modifier = Modifier.rowTextPlaceholder(16.dp)
                )
            },
            secondary = {
                Spacer(
                    modifier = Modifier.rowTextPlaceholder(
                        height = 12.dp,
                        widthFraction = 0.8f
                    )
                )
            }
        )
    })
}

/**
 * Applies the placeholder for text
 * @param height - the height of placeholder
 * @param widthFraction the fraction of a full width placeholder
 */
fun Modifier.rowTextPlaceholder(
    height: Dp,
    shape: Shape = CircleShape,
    @FloatRange(from = 0.0, to = 1.0) widthFraction: Float = 1f
) = composed {
    fillMaxWidth(widthFraction)
        .height(height)
        .padding(2.dp)
        .defaultPlaceholder(shape)
}

@Composable
fun BoxPlaceHolder(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        for (index in 0..DEFAULT_PLACEHOLDER_ROWS) {
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                Spacer(
                    modifier = Modifier
                        .weight(1f)
                        .rowTextPlaceholder(
                            shape = RoundedCornerShape(8.dp),
                            height = 320.dp,
                        )
                )
                Spacer(
                    modifier = Modifier
                        .weight(1f)
                        .rowTextPlaceholder(
                            shape = RoundedCornerShape(8.dp),
                            height = 320.dp,
                        )
                )
            }
        }
    }

}

@Preview
@Composable
private fun LoadingRowPreview() {
    BoxPlaceHolder()
}


/**
 * Setup current modifier with default shimmer values (shape, colors and animation)
 */
@Composable
fun Modifier.defaultPlaceholder(shape: Shape = RoundedCornerShape(percent = 14)) = clip(shape)
    .background(MovieTheme.colors.element.secondary)
    .placeholder(
        visible = true,
        color = MovieTheme.colors.base.secondary,
        highlight = PlaceholderHighlight.shimmer(
            highlightColor = MovieTheme.colors.base.secondaryHover,
            customShimmerAnimation()
        )
    )

/**
 * Custom animation of shimmer to remove delay of replay
 * @param durationMillis: Duration of the animation
 * @param delayMillis: Delay to start/restart animation
 * @param repeatMode: Type of repeat of animation
 */
fun customShimmerAnimation(
    durationMillis: Int = 1700,
    delayMillis: Int = 0,
    repeatMode: RepeatMode = RepeatMode.Restart
): InfiniteRepeatableSpec<Float> = infiniteRepeatable(
    animation = tween(durationMillis = durationMillis, delayMillis = delayMillis),
    repeatMode = repeatMode
)