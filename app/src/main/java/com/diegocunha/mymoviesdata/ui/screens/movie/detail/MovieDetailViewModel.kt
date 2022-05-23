package com.diegocunha.mymoviesdata.ui.screens.movie.detail

import androidx.annotation.Keep
import com.diegocunha.mymoviesdata.coroutines.DispatchersProvider
import com.diegocunha.mymoviesdata.datasource.movie.MovieDetailUseCase
import com.diegocunha.mymoviesdata.templates.GetState
import com.diegocunha.mymoviesdata.templates.StateViewModel
import com.diegocunha.mymoviesdata.templates.toGetState

class MovieDetailViewModel(
    dispatchersProvider: DispatchersProvider,
    private val movieDetailUseCase: MovieDetailUseCase,
    private val movieId: Long
) :
    StateViewModel<MovieDetailViewData>(dispatchersProvider) {

    override suspend fun fetch(): GetState<MovieDetailViewData> {
        return movieDetailUseCase(movieId).map {
            MovieDetailViewData(
                title = it.title,
                imageUrl = it.posterPath.orEmpty(),
                overview = it.overview.orEmpty()
            )
        }.toGetState()
    }
}

@Keep
data class MovieDetailViewData(
    val title: String,
    val imageUrl: String,
    val overview: String
)