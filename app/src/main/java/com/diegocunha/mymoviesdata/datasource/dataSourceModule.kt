package com.diegocunha.mymoviesdata.datasource

import com.diegocunha.mymoviesdata.coroutines.DispatchersProvider
import com.diegocunha.mymoviesdata.templates.ProductionDispatchersProvider
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.dsl.module

val dataSourceModule = module {

    single { GsonBuilder().create() }

    factory {
        OkHttpClient.Builder()
            .build()
    }

    factory<DispatchersProvider> { ProductionDispatchersProvider }
}