package com.diegocunha.mymoviesdata.datasource

import com.diegocunha.mymoviesdata.datasource.api.MyMoviesService
import com.diegocunha.mymoviesdata.datasource.movie.MoviesUseCase
import com.diegocunha.mymoviesdata.fixtures.upComingFixture
import com.diegocunha.mymoviesdata.helpers.BaseUnitTest
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MoviesUseCaseTest : BaseUnitTest() {

    private val apiService: MyMoviesService = mockk(relaxed = true)
    private lateinit var useCase: MoviesUseCase

    @Before
    fun setup() {
        clearAllMocks()
        useCase = MoviesUseCase(apiService)
    }

    @Test
    fun `WHEN network response is success THEN should inform correctly state`() = runBlockingTest {
        coEvery { apiService.upcomingMovies(any()) } returns Result.success(upComingFixture)
        val result = useCase(1)

        coVerify(exactly = 1) { apiService.upcomingMovies(any()) }
        assertTrue(result.isSuccess)
        assertNotNull(result.getOrNull())
    }

    @Test
    fun `WHEN network response is error THEN should inform correctly state`() = runBlockingTest {
        coEvery { apiService.upcomingMovies(any()) } returns Result.failure(Exception())
        val result = useCase(1)

        coVerify(exactly = 1) { apiService.upcomingMovies(any()) }
        assertTrue(result.isFailure)
        assertNull(result.getOrNull())
        assertNotNull(result.exceptionOrNull())
    }
}