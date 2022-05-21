package com.diegocunha.mymoviesdata.ui.components

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.diegocunha.mymoviesdata.R
import com.diegocunha.mymoviesdata.templates.GetFailure
import com.diegocunha.mymoviesdata.ui.theme.MovieTheme

/**
 * Default failure state for GetFailure that shows a Helper with default texts or texts given from
 * failure throwable
 * @param getFailure - state of error ui
 * @param onRetry - action triggered when the retry button is clicked
 */
@Composable
fun GetFailureHelper(
    modifier: Modifier = Modifier,
    getFailure: GetFailure,
    onRetry: () -> Unit
) {
    Helper(
        modifier = modifier.fillMaxHeight(),
        image = { FailureIcon() },
        title = { Text(stringResource(id = R.string.error)) },
        subtitle = { Text(stringResource(id = R.string.resources_error_default_subtitle)) },
        action = {
            Button(onClick = onRetry, loading = getFailure.retrying) {
                Text(stringResource(id = R.string.resources_try_again))
            }
        }
    )
}

@Composable
private fun FailureIcon() {
    HelperIcon(
        painter = painterResource(id = R.drawable.ic_reject),
        backgroundColor = MovieTheme.colors.status.alertBackground,
        statusTint = MovieTheme.colors.status.alert
    )
}