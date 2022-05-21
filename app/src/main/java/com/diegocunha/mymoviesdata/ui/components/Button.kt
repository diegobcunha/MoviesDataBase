package com.diegocunha.mymoviesdata.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonElevation
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import com.diegocunha.mymoviesdata.ui.theme.MovieTheme

/**
 * <a href="https://www.notion.so/warrenbrasil/Button-RoundedButton-Link-3234ac65fc3947fbb61367d1be900125" class="external" target="_blank">Nebraska Button</a>.
 *
 * Buttons allow the user execute an action with a single tap.
 * Its recommended to use when you want to perform an action to navigate between screens, open itens or complete actions.
 *
 * @param onClick Will be called when the user clicks the button
 * @param modifier Modifier to be applied to the button
 * @param enabled Controls the enabled state of the button. When `false`, this button will not
 * be clickable
 * @param loading When true display a loadeing state inside the button
 * @param contentPadding The spacing values to apply internally between the container and the content
 * @param interactionSource the [MutableInteractionSource] representing the stream of
 * [Interaction]s for this Button. You can create and pass in your own remembered
 * [MutableInteractionSource] if you want to observe [Interaction]s and customize the
 * appearance / behavior of this Button in different [Interaction]s.
 * @param elevation [ButtonElevation] used to resolve the elevation for this button in different
 * states. This controls the size of the shadow below the button. Pass `null` here to disable
 * elevation for this button. See [ButtonDefaults.elevation].
 * @param shape Defines the button's shape as well as its shadow
 * @param border Border to draw around the button
 * @param colors [ButtonColors] that will be used to resolve the background and content color for
 * this button in different states. See [ButtonDefaults.buttonColors].
 * @param contentPadding The spacing values to apply internally between the container and the content
 */
@Composable
fun Button(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enabled: Boolean = true,
    loading: Boolean = false,
    elevation: ButtonElevation? = ButtonDefaults.elevation(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = MaterialTheme.shapes.small,
    border: BorderStroke? = null,
    colors: ButtonColors = ButtonDefaults.buttonDefaultPrimaryColors(),
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable () -> Unit
) {
    androidx.compose.material.Button(
        onClick = {
            if (!loading) {
                onClick()
            }
        },
        modifier = modifier,
        enabled = enabled,
        interactionSource = interactionSource,
        elevation = elevation,
        shape = shape,
        border = border,
        colors = colors,
        contentPadding = contentPadding,
        content = { ButtonLoadingContent(content = content, colors = colors, loading = loading) }
    )
}

/**
 * Used to swap between the regular content and a loading state.
 *
 * @param loading display an circular indicator to indicate a load state
 * @param content display this content when loading is false
 */
@Composable
private fun ButtonLoadingContent(
    colors: ButtonColors,
    loading: Boolean = false,
    content: @Composable () -> Unit,
) {
    Box(contentAlignment = Alignment.Center) {
        val alpha: Float by animateFloatAsState(if (loading) 0f else 1f)

        if (loading) {
            LoadingCircular(
                color = colors.contentColor(
                    enabled = loading
                ).value
            )
        }

        CompositionLocalProvider(LocalContentAlpha provides alpha) {
            content()
        }
    }
}

/**
 * Create [ButtonColors] to a default state primary button
 */
@Composable
fun ButtonDefaults.buttonDefaultPrimaryColors(): ButtonColors = ButtonDefaults.buttonColors(
    backgroundColor = MovieTheme.colors.context.primary,
    contentColor = MovieTheme.colors.context.primaryHover,
    disabledBackgroundColor = MovieTheme.colors.base.disabled,
    disabledContentColor = MovieTheme.colors.base.overDisabled
)