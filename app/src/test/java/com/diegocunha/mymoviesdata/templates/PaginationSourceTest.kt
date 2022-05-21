package com.diegocunha.mymoviesdata.templates

import androidx.paging.PagingSource
import com.diegocunha.mymoviesdata.datasource.page.BasePaginationSource
import com.diegocunha.mymoviesdata.helpers.BaseUnitTest
import kotlin.test.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(JUnit4::class)
class PaginationSourceTest : BaseUnitTest() {

    private lateinit var source: FakePaginationSource

    @Before
    fun setup() {
        source = FakePaginationSource()
    }

    @Test
    fun `WHEN source returns success result THEN should return success page`() =
        runBlockingTest {

            assertEquals(
                expected = PagingSource.LoadResult.Page<Int, String>(
                    data = listOf("result"),
                    prevKey = null,
                    nextKey = null
                ),
                actual = source.load(
                    PagingSource.LoadParams.Refresh(
                        key = null,
                        loadSize = 2,
                        placeholdersEnabled = false
                    )
                ),
            )
        }

    @Test
    fun `WHEN source returns error THEN should pass correctly information`() = runBlockingTest {
        val response = PagingSource.LoadResult.Error<Int, String>(Exception("Invalid request"))
        source.result = response

        assertEquals(
            expected = response,
            actual = source.load(
                PagingSource.LoadParams.Refresh(
                    key = null,
                    loadSize = 2,
                    placeholdersEnabled = false
                )
            )
        )
    }

    inner class FakePaginationSource : BasePaginationSource<String>() {

        var result: LoadResult<Int, String> = LoadResult.Page(
            data = listOf("result"),
            prevKey = null,
            nextKey = null
        )

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, String> {
            return result
        }
    }
}