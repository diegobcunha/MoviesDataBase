package com.diegocunha.mymoviesdata.ui.screens.movie.detail

import androidx.annotation.Keep
import com.diegocunha.mymoviesdata.coroutines.DispatchersProvider
import com.diegocunha.mymoviesdata.templates.GetState
import com.diegocunha.mymoviesdata.templates.StateViewModel

class MovieDetailViewModel(dispatchersProvider: DispatchersProvider, private val movieId: Long) :
    StateViewModel<MovieDetailViewData>(dispatchersProvider) {

    override suspend fun fetch(): GetState<MovieDetailViewData> {
        TODO("Not yet implemented")
    }
}

@Keep
data class MovieDetailViewData(
    val title: String
)