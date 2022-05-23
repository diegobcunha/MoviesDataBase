package com.diegocunha.mymoviesdata.datasource.model

import com.google.gson.annotations.SerializedName

data class DiscoverMoviesResponse(
    override var page: Int,
    override val results: List<Movie>,
    @SerializedName("total_pages") override val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
) : Page<Movie>

data class Movie(
    val id: Long,
    val title: String,
    val overview: String?,
    @SerializedName("poster_path") var posterPath: String?,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("release_date") val releaseDate: String
)