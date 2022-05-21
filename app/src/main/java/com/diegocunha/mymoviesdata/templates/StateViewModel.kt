package com.diegocunha.mymoviesdata.templates

import com.diegocunha.mymoviesdata.coroutines.DispatchersProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * A base ViewModel to handle with Compose screens
 * @property T :: Data Class which will be expected to StateFlow
 */
abstract class StateViewModel<T>(dispatchersProvider: DispatchersProvider) :
    CoroutineViewModel(dispatchersProvider) {

    /**
     * State flow responsible to emit States to View
     */
    protected val _stateFlow: MutableStateFlow<GetState<T>> by lazy {
        MutableStateFlow<GetState<T>>(GetState.initial()).apply {
            launchIO {
                emit(fetch())
            }
        }
    }

    val stateFlow: StateFlow<GetState<T>> by lazy { _stateFlow.asStateFlow() }

    /**
     * Function which will be implemented by other classes to make network calls
     */
    abstract suspend fun fetch(): GetState<T>

    /**
     * Retry method in cases where the network call fails
     */
    fun retry() {
        launchIO {
            _stateFlow.retryState(::fetch)
        }
    }
}