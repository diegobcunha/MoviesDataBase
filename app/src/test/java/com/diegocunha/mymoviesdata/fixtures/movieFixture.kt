package com.diegocunha.mymoviesdata.fixtures

import com.diegocunha.mymoviesdata.datasource.model.DiscoverMoviesResponse
import com.diegocunha.mymoviesdata.datasource.model.Movie
import com.diegocunha.mymoviesdata.datasource.movie.MovieResponse
import com.diegocunha.mymoviesdata.datasource.movie.MovieViewModel

val movieResponse = MovieResponse(
    false,
    listOf(
        MovieViewModel(
            1,
            "title",
            "overview",
            "poster",
            "releaseDate"
        )
    )
)
val movie = Movie(
    1,
    "title",
    "overview",
    "posterPath",
    "backgropPath",
    "releaseDate"
)

val movies = listOf(movie)

val upComingFixture = DiscoverMoviesResponse(
    1,
    movies,
    1,
    20
)

val movieViewModel = MovieViewModel(
    1L,
    "title",
    "overview",
    "posterPath",
    "releaseDate"
)

val movieViewModelList = listOf(movieViewModel)