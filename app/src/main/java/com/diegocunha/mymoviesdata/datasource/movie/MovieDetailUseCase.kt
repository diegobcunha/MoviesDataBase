package com.diegocunha.mymoviesdata.datasource.movie

import com.diegocunha.mymoviesdata.datasource.api.MyMoviesService
import com.diegocunha.mymoviesdata.datasource.mapper.MovieMapper

class MovieDetailUseCase(
    private val apiService: MyMoviesService,
    private val movieMapper: MovieMapper
) {

    suspend operator fun invoke(id: Long): Result<MovieViewModel> {
        return apiService.movie(id).map {
            movieMapper(it)
        }
    }
}