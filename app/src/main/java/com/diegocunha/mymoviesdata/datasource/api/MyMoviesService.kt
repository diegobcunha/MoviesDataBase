package com.diegocunha.mymoviesdata.datasource.api

import com.diegocunha.mymoviesdata.datasource.model.GenreResponse
import com.diegocunha.mymoviesdata.datasource.model.Movie
import com.diegocunha.mymoviesdata.datasource.model.UpcomingMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MyMoviesService {

    @GET("genre/movie/list")
    suspend fun genres(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Result<GenreResponse>

    @GET("movie/upcoming")
    suspend fun upcomingMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int,
        @Query("region") region: String
    ): Result<UpcomingMoviesResponse>

    @GET("movie/{id}")
    suspend fun movie(
        @Path("id") id: Long,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Result<Movie>
}