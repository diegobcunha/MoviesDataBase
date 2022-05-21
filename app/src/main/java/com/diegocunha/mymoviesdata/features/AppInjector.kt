package com.diegocunha.mymoviesdata.features

import android.app.Application
import com.diegocunha.mymoviesdata.datasource.dataSourceModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.module.Module

object AppInjector {

    fun inject(application: Application) {
        application.startInjection(
            dataSourceModule
        )
    }
}

private fun Application.startInjection(vararg modules: Module) {
    startKoin {
        // Changed from Level.INFO to Level.ERROR as a workaround for kotlin 1.4 and the koin lib
        androidLogger(Level.ERROR)
        androidContext(this@startInjection)
        modules(listOf(*modules))
    }
}