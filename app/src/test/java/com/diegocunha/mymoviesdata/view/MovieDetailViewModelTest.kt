package com.diegocunha.mymoviesdata.view

import app.cash.turbine.test
import com.diegocunha.mymoviesdata.datasource.movie.MovieDetailUseCase
import com.diegocunha.mymoviesdata.fixtures.movieViewModel
import com.diegocunha.mymoviesdata.helpers.BaseUnitTest
import com.diegocunha.mymoviesdata.templates.GetStatus
import com.diegocunha.mymoviesdata.ui.screens.movie.detail.MovieDetailViewModel
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MovieDetailViewModelTest : BaseUnitTest() {

    private lateinit var viewModel: MovieDetailViewModel
    private val movieDetailUseCase = mockk<MovieDetailUseCase>()

    @Before
    fun setup() {
        clearAllMocks()
        viewModel = MovieDetailViewModel(testDispatchersProvider, movieDetailUseCase, 1)
    }

    @Test
    fun `WHEN network response is success THEN should emit valid response`() = runBlockingTest {
        coEvery { movieDetailUseCase(any()) } returns Result.success(movieViewModel)
        viewModel.stateFlow.test {
            val item = awaitItem()
            assertEquals(item.currentStatus(), GetStatus.SUCCESS)
            val response = item.success
            assertNotNull(response)

            assertEquals(response.title, movieViewModel.title)
            assertEquals(response.imageUrl, movieViewModel.posterPath)
            assertEquals(response.overview, movieViewModel.overview)
        }
    }

    @Test
    fun `WHEN network response is failure THEN should emit valid response`() = runBlockingTest {
        coEvery { movieDetailUseCase(any()) } returns Result.failure(Exception("Invalid request"))
        viewModel.stateFlow.test {
            val item = awaitItem()
            assertEquals(item.currentStatus(), GetStatus.FAILURE)
            val response = item.failure.throwable
            assertNotNull(response)

            assertEquals(response.message, "Invalid request")
        }
    }

}