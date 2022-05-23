package com.diegocunha.mymoviesdata.ui.screens.movie.detail

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.diegocunha.mymoviesdata.templates.GetState
import com.diegocunha.mymoviesdata.ui.components.BackNavigationIcon
import com.diegocunha.mymoviesdata.ui.components.DefaultScaffoldTopBar
import com.diegocunha.mymoviesdata.ui.components.GetCrossfade
import com.diegocunha.mymoviesdata.ui.components.GetFailureHelper
import com.diegocunha.mymoviesdata.ui.components.PlaceholderFullScreen
import com.diegocunha.mymoviesdata.ui.components.TwoLineRowListPlaceholder
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun MovieDetailScreen(title: String, id: Long) {
    val viewModel = getViewModel<MovieDetailViewModel>() {
        parametersOf(id)
    }

    val viewState by viewModel.stateFlow.collectAsState()

    Screen(title, viewState, viewModel::retry)
}

@Composable
private fun Screen(title: String, state: GetState<MovieDetailViewData>, onRetry: () -> Unit) {
    DefaultScaffoldTopBar(title = { Text(title) },
        navigationIcon = { BackNavigationIcon() },
        body = {
            GetCrossfade(
                state = state,
                initial = {
                    PlaceholderFullScreen {
                        TwoLineRowListPlaceholder()
                    }
                },
                failure = {
                    GetFailureHelper(getFailure = it, onRetry = onRetry)
                }
            ) {

            }
        }
    )
}