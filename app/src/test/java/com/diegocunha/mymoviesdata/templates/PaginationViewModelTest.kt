package com.diegocunha.mymoviesdata.templates

import androidx.paging.PagingData
import app.cash.turbine.test
import com.diegocunha.mymoviesdata.coroutines.DispatchersProvider
import com.diegocunha.mymoviesdata.helpers.BaseUnitTest
import kotlin.test.assertEquals
import kotlin.time.ExperimentalTime
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
@OptIn(ExperimentalTime::class, ExperimentalCoroutinesApi::class)
class PaginationViewModelTest : BaseUnitTest() {

    private lateinit var viewModel: FakePaginationViewModel

    @Before
    fun setup() {
        viewModel = FakePaginationViewModel(testDispatchersProvider, 10)
    }

    @Test
    fun `WHEN ViewModel is started THEN should get values`() = runBlockingTest {

        val response = listOf("string1", "string2", "string3")
        val pagingData = PagingData.from(response)

        viewModel.response = flow {
            emit(pagingData)
        }

        viewModel.response.test {
            val item = awaitItem()
            assertEquals(pagingData, item)
            awaitComplete()
        }
    }

    @Test
    fun `WHEN view request 10 items THEN should pass 10 items to function`() = runBlockingTest {
        val response = listOf("string1", "string2", "string3")
        val pagingData = PagingData.from(response)

        viewModel.response = flow {
            emit(pagingData)
        }

        assertEquals(10, viewModel.size)
    }

    @Test
    fun `WHEN response is empty THEN should emit empty page`() = runBlockingTest {
        val item = PagingData.empty<String>()
        viewModel.response = flow {
            emit(item)
        }

        viewModel.response.test {
            val pagingItem = awaitItem()
            assertEquals(item, pagingItem)
            assertEquals(10, viewModel.size)
            awaitComplete()
        }
    }

    inner class FakePaginationViewModel(
        dispatchersProvider: DispatchersProvider,
        pageSize: Int
    ) : PaginationViewModel<String>(dispatchersProvider, pageSize) {

        var response: Flow<PagingData<String>> = emptyFlow()
        val size = pageSize

        override fun fetchContent(initialPageSize: Int): Flow<PagingData<String>> {
            return response
        }
    }
}