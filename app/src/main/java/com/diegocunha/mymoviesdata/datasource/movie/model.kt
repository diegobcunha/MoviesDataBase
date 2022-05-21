package com.diegocunha.mymoviesdata.datasource.movie

data class MovieViewModel(
    val id: Long,
    val title: String,
    val overview: String?,
    val posterPath: String?,
    val releaseDate: String
)

data class MovieResponse(
    val hasMoreItemsToShow: Boolean,
    val items: List<MovieViewModel>
)