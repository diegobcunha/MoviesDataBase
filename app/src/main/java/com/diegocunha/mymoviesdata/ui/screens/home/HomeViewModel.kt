package com.diegocunha.mymoviesdata.ui.screens.home

import androidx.annotation.Keep
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.diegocunha.mymoviesdata.coroutines.DispatchersProvider
import com.diegocunha.mymoviesdata.coroutines.transformPagingData
import com.diegocunha.mymoviesdata.datasource.movie.MoviesSource
import com.diegocunha.mymoviesdata.templates.PaginationViewModel
import kotlinx.coroutines.flow.Flow

class HomeViewModel(
    dispatchersProvider: DispatchersProvider,
    private val movieSource: MoviesSource
) :
    PaginationViewModel<MovieViewData>(dispatchersProvider, 100) {

    override fun fetchContent(initialPageSize: Int): Flow<PagingData<MovieViewData>> {
        return Pager(
            PagingConfig(
                pageSize = 100,
                prefetchDistance = 2,
                initialLoadSize = 100
            )
        ) { movieSource }.flow.transformPagingData {
            MovieViewData(it.id, it.title, it.overview, it.posterPath)
        }
    }
}

@Keep
data class MovieViewData(
    val id: Long,
    val title: String,
    val subtitle: String?,
    val poster: String?,
)