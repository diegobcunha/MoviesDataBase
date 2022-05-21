package com.diegocunha.mymoviesdata.datasource.movie

import com.diegocunha.mymoviesdata.datasource.page.BasePaginationSource

class MoviesSource(private val service: MoviesUseCase) : BasePaginationSource<MovieViewModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieViewModel> {
        val nextPage = params.key ?: 1

        val response = service(nextPage)
        return if (response.isSuccess) {
            LoadResult.Page(
                data = response.getOrNull()?.items.orEmpty(),
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (response.getOrNull()?.hasMoreItemsToShow == true) nextPage + 1 else null
            )
        } else {
            LoadResult.Error(response.exceptionOrNull() ?: Exception("Invalid request"))
        }
    }
}