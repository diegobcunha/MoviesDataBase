package com.diegocunha.mymoviesdata.templates

import kotlin.test.assertEquals
import kotlin.test.assertFalse
import org.junit.Test

class GetStateExtensionsTest {

    @Test
    fun `GIVEN a failure state WHEN mapped to GetState THEN a GetState failure should be returned`() {
        val expected = Exception()
        val failure = Result.failure<String>(expected).toGetState().failure
        assertEquals(expected, failure.throwable)
        assertFalse(failure.retrying)
    }

    @Test
    fun `GIVEN a success result THEN should be mapped to different response when necessary`() {
        val response = 1
        val request = GetState.success("success").map { response }
        assertEquals(response, request.success)
    }

    @Test
    fun `GIVEN a failure state THEN should be mapped to different response when necessary`() {
        val throwable = Throwable("any-cause")
        val response = 1
        val state = GetState.failure<String>(throwable).map { response }
        assertEquals(
            GetState.failure(state.failure),
            state.map { response }
        )
    }

    @Test
    fun `GIVEN a loading state THEN should be mapped to different response when necessary`() {
        val response = 1
        val state = GetState.initial<String>().map { response }
        assertEquals(
            GetState.initial(),
            state.map { response }
        )
    }
}