package com.diegocunha.mymoviesdata.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.diegocunha.mymoviesdata.ui.theme.MovieTheme

/**
 * Helper to indicates some status and give an emphasis to the situation, sometimes used to provides
 * instructions to user or some important alerts.
 * Typically it's used to show some error indication or finishing some feature flow.
 *
 * @param modifier - The modifier to be applied to the Column
 * @param image - A simple vector icon or animation to indicates whats happening
 * @param title - Main message with high emphasis. Typically it's a Text
 * @param subtitle - Contextual description  with low emphasis about the motivation to show this
 * Helper to user
 * @param action - optional action to user. Typically it's a Button or loading button
 */
@Composable
fun Helper(
    modifier: Modifier = Modifier,
    image: @Composable () -> Unit,
    title: @Composable () -> Unit,
    subtitle: @Composable () -> Unit,
    action: @Composable (() -> Unit)? = null
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                image()
            }
            StyledText(
                textStyle = MovieTheme.typography.h1.copy(fontSize = 32.sp, textAlign = TextAlign.Center),
                contentColor = MovieTheme.colors.text.primary,
                alpha = ContentAlpha.high,
                content = title
            )
            Box(modifier = Modifier.padding(horizontal = 32.dp)) {
                StyledText(
                    textStyle = MovieTheme.typography.p4.copy(textAlign = TextAlign.Center),
                    contentColor = MovieTheme.colors.text.secondary,
                    alpha = ContentAlpha.medium,
                    content = subtitle
                )
            }
            action?.invoke()
        }
    }
}

/**
 * Default icon setup for Helper, using the right size, shape and alignment for painters
 *
 * @param modifier Modifier to be used at box
 * @param painter - the painter icon to be used
 * @param backgroundColor - color of circular background
 * @param statusTint - color to be applied as painter tint
 */
@Composable
fun HelperIcon(
    modifier: Modifier = Modifier,
    painter: Painter,
    backgroundColor: Color,
    statusTint: Color = backgroundColor
) {
    Box(
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = CircleShape
            )
            .size(48.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painter,
            contentDescription = null,
            tint = statusTint
        )
    }
}