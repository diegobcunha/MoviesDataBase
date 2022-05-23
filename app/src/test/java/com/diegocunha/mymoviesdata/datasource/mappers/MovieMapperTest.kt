package com.diegocunha.mymoviesdata.datasource.mappers

import com.diegocunha.mymoviesdata.datasource.mapper.ImageMapper
import com.diegocunha.mymoviesdata.datasource.mapper.MovieMapper
import com.diegocunha.mymoviesdata.fixtures.movie
import com.diegocunha.mymoviesdata.helpers.BaseUnitTest
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MovieMapperTest : BaseUnitTest() {

    private val imageMapper = ImageMapper()

    @Test
    fun `WHEN a movie mapper is called THEN should parse information correctly`() {
        val movieModel = MovieMapper(imageMapper)
        val request = movieModel(movie)

        assertEquals(request.id, movie.id)
        assertEquals(request.overview, movie.overview)
        assertEquals(request.releaseDate, movie.releaseDate)
        assertEquals(request.title, movie.title)
        assertTrue(request.posterPath.orEmpty().contains(movie.posterPath.orEmpty()))

    }
}