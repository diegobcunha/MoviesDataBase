package com.diegocunha.mymoviesdata.datasource.api

import com.diegocunha.mymoviesdata.datasource.model.DiscoverMoviesResponse
import com.diegocunha.mymoviesdata.datasource.model.Movie
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MyMoviesService {

    @GET("discover/movie")
    suspend fun upcomingMovies(
        @Query("page") page: Int
    ): Result<DiscoverMoviesResponse>

    @GET("movie/{id}")
    suspend fun movie(
        @Path("id") id: Long,
    ): Result<Movie>
}