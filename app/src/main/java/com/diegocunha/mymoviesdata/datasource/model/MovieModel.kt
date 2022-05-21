package com.diegocunha.mymoviesdata.datasource.model

import com.google.gson.annotations.SerializedName

data class GenreResponse(val genres: List<Genre>)

data class Genre(val id: Int, val name: String)

data class UpcomingMoviesResponse(
    override var page: Int,
    override val results: List<Movie>,
    @SerializedName("total_pages") override val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
): Page<Movie>

data class Movie(
    val id: Long,
    val title: String,
    val overview: String?,
    val genres: List<Genre>?,
    @SerializedName("genre_ids") val genreIds: List<Int>?,
    @SerializedName("poster_path") var posterPath: String?,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("release_date") val releaseDate: String
)