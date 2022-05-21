package com.diegocunha.mymoviesdata.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.unit.dp
import com.diegocunha.mymoviesdata.ui.theme.MovieTheme

/**
 * A RegularRow displays a surface area related to raw data, menu items, and any basic list content.
 * It's the most common implementation of [FoundationRow], containing slots for title, detail,
 * rightAction and leftIcon.
 *
 * @param modifier - The modifier to be applied to the RegularRow.
 * @param endIcon - First placed composable. This should typically be an Icon with small size or
 * an Image. The default layout here is a [Row], so icon can be aligned centralized if is an
 * avatar or top aligned if is an small icon.
 * @param endContent - contextual content to complements the title. This should typically be a
 * Text or [TwoLine]
 * display
 * @param startIcon - The right most aligned composable. Used to be an Icon with small size that
 * indicates the action of the clickable Row, such as a navigation icon or a copy paste icon.
 * @param startContent - Second left placed composable and main space, necessary for all rows. This should
 * typically be a Text or [TwoLine]
 */
@Composable
fun RegularRow(
    modifier: Modifier = Modifier,
    endIcon: @Composable (RowScope.() -> Unit)? = null,
    startContent: @Composable () -> Unit,
    endContent: @Composable (() -> Unit)? = null,
    startIcon: @Composable (RowScope.() -> Unit)? = null,
) {
    FoundationRow(
        modifier = modifier.wrapContentHeight()
    ) {
        startIcon?.let {
            startIcon()
            Spacer(modifier = Modifier.width(16.dp))
        }
        Box(
            modifier = Modifier.weight(1F),
        ) {
            StyledText(textStyle = MovieTheme.typography.p3, content = startContent)
        }
        endContent?.let {
            Box(
                modifier = Modifier.wrapContentWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                StyledText(textStyle = MovieTheme.typography.p3, content = endContent)
            }
        }

        endIcon?.let {
            Spacer(modifier = Modifier.width(16.dp))
            endIcon()
        }
    }
}

/**
 * A TwoLine displays two slots vertically, applying a primary and a secondary text emphasis.
 * It's typically used in [RegularRow] title or detail params, as an alternative to Text composable.
 * It's strongly inspired in native ListItem composable, but without icon setup
 *
 * @param modifier - The modifier to be applied to the TwoLine.
 * @param primary - The top aligned composable. This should typically be an Text
 * @param secondary - The bottom aligned composable. This should typically be an Text but also can
 * be a Chip
 * @param horizontalAlignment - Specifies when the content should be left-aligned or right-aligned
 */
@Composable
fun TwoLine(
    modifier: Modifier = Modifier,
    primary: @Composable () -> Unit,
    secondary: @Composable () -> Unit,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start
) {
    Column(modifier = modifier, horizontalAlignment = horizontalAlignment) {
        StyledText(textStyle = MovieTheme.typography.p3, content = primary)
        StyledText(
            textStyle = MovieTheme.typography.p5,
            content = secondary
        )
    }
}

/**
 * Base implementation of a [Row] used to apply a surface area and default paddings.
 * See (https://www.figma.com/file/POSO4IJ8uhT69HgFTCz27z/Rows?node-id=299%3A2047) for official
 * documentation
 *
 * @param modifier - The modifier to be applied to the FoundationRow.
 */
@Composable
fun FoundationRow(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    Surface {
        Row(
            modifier = modifier.composed {
                wrapContentHeight()
                    .fillMaxWidth()
                    .padding(
                        horizontal = 16.dp,
                        vertical = 16.dp
                    )
            }
        ) {
            content()
        }
    }
}