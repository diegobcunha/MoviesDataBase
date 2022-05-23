package com.diegocunha.mymoviesdata.ui.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.GridItemSpan
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material.icons.filled.Movie
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberAsyncImagePainter
import com.diegocunha.mymoviesdata.R
import com.diegocunha.mymoviesdata.ui.components.BoxPlaceHolder
import com.diegocunha.mymoviesdata.ui.components.CardBox
import com.diegocunha.mymoviesdata.ui.components.DefaultScaffoldTopBar
import com.diegocunha.mymoviesdata.ui.components.FailureHelper
import com.diegocunha.mymoviesdata.ui.components.ImageLoader
import com.diegocunha.mymoviesdata.ui.components.PlaceholderFullScreen
import com.diegocunha.mymoviesdata.ui.components.renderError
import com.diegocunha.mymoviesdata.ui.components.renderLoading
import com.diegocunha.mymoviesdata.ui.theme.MovieTheme
import org.koin.androidx.compose.getViewModel

private const val COLUMN_COUNT = 2
private val GRID_SPACING = 8.dp

@Composable
internal fun HomeScreen(onMovieSelected: (Pair<String, Long>) -> Unit) {
    val viewModel = getViewModel<HomeViewModel>()
    Screen(viewModel = viewModel, onMovieSelected)
}

@Composable
private fun Screen(viewModel: HomeViewModel, onMovieSelected: (Pair<String, Long>) -> Unit) {
    val items = viewModel.pagingFlow.collectAsLazyPagingItems()

    val listState = rememberLazyListState()

    DefaultScaffoldTopBar(
        title = { Text(text = stringResource(id = R.string.app_name)) },
        body = {
            MovieContentBody(items, listState, onMovieSelected)
        }
    )
}

@Composable
private fun MovieContentBody(
    items: LazyPagingItems<MovieViewData>,
    listState: LazyListState,
    onMovieSelected: (Pair<String, Long>) -> Unit
) {
    when (items.loadState.refresh) {
        is LoadState.Loading -> {
            PlaceholderFullScreen {
                BoxPlaceHolder()
            }
        }
        is LoadState.Error -> {
            FailureHelper(onRetry = { items.retry() })
        }
        else -> {
            MovieGridList(items, listState, onMovieSelected)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun MovieGridList(
    items: LazyPagingItems<MovieViewData>,
    listState: LazyListState,
    onMovieSelected: (Pair<String, Long>) -> Unit
) {
    LazyVerticalGrid(
        state = listState,
        cells = GridCells.Fixed(COLUMN_COUNT),
        horizontalArrangement = Arrangement.spacedBy(GRID_SPACING, Alignment.CenterHorizontally),
        contentPadding = PaddingValues(
            start = GRID_SPACING,
            end = GRID_SPACING,
            bottom = 4.dp
        ),
        content = {
            items(items.itemCount) { movieIndex ->
                val movie = items.peek(movieIndex) ?: return@items
                Movie(movie, onClick = {
                    onMovieSelected(movie.title to movie.id)
                })

                renderLoading(
                    modifier = Modifier.padding(vertical = GRID_SPACING),
                    span = { GridItemSpan(COLUMN_COUNT) },
                    loadState = items.loadState
                )

                renderError(
                    modifier = Modifier.padding(vertical = GRID_SPACING),
                    span = { GridItemSpan(COLUMN_COUNT) },
                    loadState = items.loadState
                )
            }
        }
    )
}

@Composable
fun Movie(movie: MovieViewData, onClick: (Long) -> Unit) {
    CardBox(
        modifier = Modifier
            .height(360.dp)
            .padding(vertical = GRID_SPACING),
        onClick = { onClick(movie.id) },
        content = {
            Box {
                ImageLoader(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxSize(),
                    painter = rememberAsyncImagePainter(
                        model = movie.poster,
                        error = rememberVectorPainter(Icons.Filled.BrokenImage),
                        placeholder = rememberVectorPainter(Icons.Default.Movie)
                    ),
                    contentDescription = movie.title,
                )


                Spacer(modifier = Modifier.height(4.dp))

                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .background(MovieTheme.colors.base.overImage)
                        .padding(horizontal = 6.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = movie.title,
                        style = MovieTheme.typography.p5,
                        color = MovieTheme.colors.text.overImage,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                }
            }
        }
    )
}