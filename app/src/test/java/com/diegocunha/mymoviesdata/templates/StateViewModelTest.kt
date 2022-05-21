package com.diegocunha.mymoviesdata.templates

import app.cash.turbine.test
import com.diegocunha.mymoviesdata.coroutines.DispatchersProvider
import com.diegocunha.mymoviesdata.helpers.BaseUnitTest
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.time.ExperimentalTime
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
@ExperimentalTime
class StateViewModelTest: BaseUnitTest() {

    private lateinit var fakeViewModel: FakeStateViewModel

    @Before
    fun setup() {
        fakeViewModel = FakeStateViewModel(testDispatchersProvider)
    }

    @Test
    @ExperimentalTime
    fun `WHEN ViewModel is initialized THEN service must be called`() = runBlockingTest {

        fakeViewModel.stateFlow.test {
            val item = awaitItem()
            assertEquals(Unit, item.success)
        }

        assertTrue(fakeViewModel.counter == 1)
    }

    @Test
    fun `WHEN ViewModel returns error and retry is called THEN service should be called twice`() =
        runBlockingTest {

            fakeViewModel.response = GetState.Companion.failure(Exception())

            fakeViewModel.stateFlow.test {
                val item = awaitItem()
                assertEquals(GetStatus.FAILURE, item.currentStatus())
            }

            fakeViewModel.retry()
            assertTrue(fakeViewModel.counter == 2)
        }

    inner class FakeStateViewModel(dispatchersProvider: DispatchersProvider) :
        StateViewModel<Unit>(dispatchersProvider) {

        var counter: Int = 0
        var response: GetState<Unit> = GetState.success(Unit)

        override suspend fun fetch(): GetState<Unit> {
            counter++
            return response
        }
    }
}