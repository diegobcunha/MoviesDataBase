package com.diegocunha.mymoviesdata.ui.screens.movie.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material.icons.filled.Movie
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.diegocunha.mymoviesdata.templates.GetState
import com.diegocunha.mymoviesdata.ui.components.BackNavigationIcon
import com.diegocunha.mymoviesdata.ui.components.DefaultScaffoldTopBar
import com.diegocunha.mymoviesdata.ui.components.GetCrossfade
import com.diegocunha.mymoviesdata.ui.components.GetFailureHelper
import com.diegocunha.mymoviesdata.ui.components.ImageLoader
import com.diegocunha.mymoviesdata.ui.components.PlaceholderFullScreen
import com.diegocunha.mymoviesdata.ui.components.StyledText
import com.diegocunha.mymoviesdata.ui.components.TwoLine
import com.diegocunha.mymoviesdata.ui.components.TwoLineRowListPlaceholder
import com.diegocunha.mymoviesdata.ui.theme.MovieTheme
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
                MovieDetailScreenBody(movieViewData = it)
            }
        }
    )
}

@Composable
fun MovieDetailScreenBody(movieViewData: MovieDetailViewData) {
    LazyColumn {
        item {
            ImageLoader(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(480.dp),
                painter = rememberAsyncImagePainter(
                    model = movieViewData.imageUrl,
                    error = rememberVectorPainter(Icons.Filled.BrokenImage),
                    placeholder = rememberVectorPainter(Icons.Default.Movie)
                ), contentDescription = movieViewData.title
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        item {
            Box(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                TwoLine(
                    primary = {
                        StyledText(textStyle = MovieTheme.typography.h1) {
                            Text(movieViewData.title)
                        }
                    },
                    secondary = { Text(text = movieViewData.overview) })
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}