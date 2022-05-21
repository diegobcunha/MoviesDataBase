package com.diegocunha.mymoviesdata.templates

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diegocunha.mymoviesdata.coroutines.DispatchersProvider
import com.diegocunha.mymoviesdata.coroutines.ScopedContextDispatchers
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.launch

open class CoroutineViewModel(protected val dispatchersProvider: DispatchersProvider): ViewModel() {

    protected val scopedContextProvider =
        ScopedContextDispatchers(
            viewModelScope,
            dispatchersProvider
        )

    fun io(): CoroutineContext = scopedContextProvider.io
    fun main(): CoroutineContext = scopedContextProvider.main

    fun launchIO(
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
    ) = launch(io(), start, block)

    fun launchMain(
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
    ) = launch(main(), start, block)

    fun launch(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
    ) = viewModelScope.launch(context, start, block)

}