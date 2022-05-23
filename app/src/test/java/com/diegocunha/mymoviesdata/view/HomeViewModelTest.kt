package com.diegocunha.mymoviesdata.view

import androidx.paging.PagingSource
import com.diegocunha.mymoviesdata.datasource.movie.MoviesSource
import com.diegocunha.mymoviesdata.fixtures.movieViewModelList
import com.diegocunha.mymoviesdata.helpers.BaseUnitTest
import com.diegocunha.mymoviesdata.ui.screens.home.HomeViewModel
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class HomeViewModelTest : BaseUnitTest() {

    private lateinit var viewModel: HomeViewModel
    private val movieSource = mockk<MoviesSource>()

    @Before
    fun setup() {
        clearAllMocks()
        viewModel = HomeViewModel(testDispatchersProvider, movieSource)
    }

    @Test
    fun `WHEN returns success data THEN should return valid information`() = runBlockingTest {
        coEvery { movieSource.load(any()) } returns PagingSource.LoadResult.Page(
            data = movieViewModelList,
            null,
            null
        )

        coVerify(exactly = 1) { movieSource.load(any()) }
    }

    @Test
    fun `WHEN returns error data THEN should return valid information`() = runBlockingTest {
        coEvery { movieSource.load(any()) } returns PagingSource.LoadResult.Error(
            Exception()
        )

        coVerify(exactly = 1) { movieSource.load(any()) }
    }
}