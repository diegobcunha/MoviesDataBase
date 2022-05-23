package com.diegocunha.mymoviesdata.datasource.movie

import com.diegocunha.mymoviesdata.datasource.api.MyMoviesService
import com.diegocunha.mymoviesdata.datasource.mapper.MovieMapper
import com.diegocunha.mymoviesdata.datasource.model.Movie

/**
 * A class to map response and transform informations
 */
class MoviesUseCase(private val apiService: MyMoviesService, private val movieMapper: MovieMapper) {

    suspend operator fun invoke(page: Int): Result<MovieResponse> {
        return apiService.upcomingMovies(page).map {
            MovieResponse(
                hasMoreItemsToShow = page < it.totalPages,
                items = it.results.toMovieViewModel()
            )
        }
    }

    private fun List<Movie>.toMovieViewModel() = map {
        movieMapper(it)
    }
}