package com.diegocunha.mymoviesdata.fixtures

import com.diegocunha.mymoviesdata.datasource.model.Movie
import com.diegocunha.mymoviesdata.datasource.model.UpcomingMoviesResponse

val movie = Movie(
    1,
    "title",
    "overview",
    "posterPath",
    "backgropPath",
    "releaseDate"
)

val movies = listOf(movie)

val upComingFixture = UpcomingMoviesResponse(
    1,
    movies,
    1,
    20
)