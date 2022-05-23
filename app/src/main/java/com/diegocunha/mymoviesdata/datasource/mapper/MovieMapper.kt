package com.diegocunha.mymoviesdata.datasource.mapper

import com.diegocunha.mymoviesdata.datasource.model.Movie
import com.diegocunha.mymoviesdata.datasource.movie.MovieViewModel

class MovieMapper(private val imageMapper: ImageMapper) {

    operator fun invoke(movie: Movie) = MovieViewModel(
        id = movie.id,
        title = movie.title,
        overview = movie.overview,
        releaseDate = movie.releaseDate,
        posterPath = movie.posterPath?.let {
            imageMapper(it)
        }
    )
}