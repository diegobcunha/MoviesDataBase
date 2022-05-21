package com.diegocunha.mymoviesdata

import android.app.Application
import com.diegocunha.mymoviesdata.features.AppInjector

class MyMoviesDataApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        AppInjector.inject(this)
    }
}