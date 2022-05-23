package com.diegocunha.mymoviesdata.datasource

import com.diegocunha.mymoviesdata.datasource.api.MyMoviesService
import com.diegocunha.mymoviesdata.datasource.mapper.ImageMapper
import com.diegocunha.mymoviesdata.datasource.mapper.MovieMapper
import com.diegocunha.mymoviesdata.datasource.movie.MovieDetailUseCase
import com.diegocunha.mymoviesdata.fixtures.movie
import com.diegocunha.mymoviesdata.helpers.BaseUnitTest
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlin.test.assertNotNull
import kotlin.test.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MovieDetailUseCaseTest : BaseUnitTest() {

    private lateinit var useCase: MovieDetailUseCase
    private val apiService = mockk<MyMoviesService>(relaxed = true)
    private val mapper = MovieMapper(ImageMapper())

    @Before
    fun setup() {
        clearAllMocks()
        useCase = MovieDetailUseCase(apiService, mapper)
    }

    @Test
    fun `WHEN useCase is called with success response THEN should return valid data`() =
        runBlockingTest {
            coEvery { apiService.movie(any()) } returns Result.success(movie)
            val response = useCase(1)

            assertTrue(response.isSuccess)
            assertNotNull(response.getOrNull())
            coVerify(exactly = 1) { apiService.movie(any()) }

        }

    @Test
    fun `WHEN useCase is called with error response THEN should return valid exception`() =
        runBlockingTest {
            coEvery { apiService.movie(any()) } returns Result.failure(Exception("Invalid message"))
            val response = useCase(1)

            assertTrue(response.isFailure)
            assertNotNull(response.exceptionOrNull())
            coVerify(exactly = 1) { apiService.movie(any()) }
        }
}