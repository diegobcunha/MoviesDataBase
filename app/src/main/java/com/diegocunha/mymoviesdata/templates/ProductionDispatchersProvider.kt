package com.diegocunha.mymoviesdata.templates

import com.diegocunha.mymoviesdata.coroutines.DispatchersProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Provides the production dispatchers
 */
object ProductionDispatchersProvider :
    DispatchersProvider {
    override val main: CoroutineDispatcher = Dispatchers.Main
    override val io: CoroutineDispatcher = Dispatchers.IO
}