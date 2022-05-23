package com.diegocunha.mymoviesdata.datasource

import androidx.paging.PagingSource
import com.diegocunha.mymoviesdata.datasource.movie.MoviesSource
import com.diegocunha.mymoviesdata.datasource.movie.MoviesUseCase
import com.diegocunha.mymoviesdata.fixtures.movieResponse
import com.diegocunha.mymoviesdata.helpers.BaseUnitTest
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MovieSourceTest: BaseUnitTest() {

    private val useCase = mockk<MoviesUseCase>(relaxed = true)
    private lateinit var source: MoviesSource

    @Before
    fun setup() {
        clearAllMocks()
        source = MoviesSource(useCase)
    }

    @Test
    fun `WHEN load is called for source THEN should call usecase`() = runBlockingTest {
        val moviesResponse = Result.success(movieResponse)
        coEvery { useCase(any()) } returns moviesResponse
        val loadParams = mockk<PagingSource.LoadParams<Int>> {
            every { key } returns 1
        }

        source.load(loadParams)

        coVerify { useCase(1) }
    }

}