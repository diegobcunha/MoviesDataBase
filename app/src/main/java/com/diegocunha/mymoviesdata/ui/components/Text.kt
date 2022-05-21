package com.diegocunha.mymoviesdata.ui.components

import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.diegocunha.mymoviesdata.ui.theme.MovieTheme

/**
 * Apply common text styles properties to the given content.
 * It's useful for components working with slots to apply specific text or icon styles to component
 * areas that can be support multiple compositions
 *
 * @param textStyle - TextStyle to be applied
 * @param alpha - Alpha to be applied
 * @param contentColor - Color to be applied
 */
@Composable
fun StyledText(
    textStyle: TextStyle,
    alpha: Float = LocalContentAlpha.current,
    contentColor: Color = MovieTheme.colors.text.primary,
    content: @Composable (() -> Unit)
) {
    CompositionLocalProvider(
        LocalContentAlpha provides alpha,
        LocalContentColor provides contentColor,
    ) {
        ProvideTextStyle(textStyle, content)
    }
}