package com.diegocunha.mymoviesdata.datasource.movie

import com.diegocunha.mymoviesdata.datasource.api.MyMoviesService
import com.diegocunha.mymoviesdata.datasource.model.Movie

/**
 * A class to map response and transform informations
 */
class MoviesUseCase(private val apiService: MyMoviesService) {

    suspend operator fun invoke(page: Int): Result<MovieResponse> {
        return apiService.upcomingMovies(page).map {
            MovieResponse(
                hasMoreItemsToShow = page < it.totalPages,
                items = it.results.toMovieViewModel()
            )
        }
    }

    private fun List<Movie>.toMovieViewModel() = map {
        MovieViewModel(
            id = it.id,
            title = it.title,
            overview = it.overview,
            releaseDate = it.releaseDate,
            posterPath = it.posterPath?.let {
                "https://image.tmdb.org/t/p/w500/$it"
            }
        )
    }
}