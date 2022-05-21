package com.diegocunha.mymoviesdata.datasource

import com.diegocunha.mymoviesdata.BuildConfig
import com.diegocunha.mymoviesdata.coroutines.DispatchersProvider
import com.diegocunha.mymoviesdata.datasource.api.CallAdapterFactory
import com.diegocunha.mymoviesdata.datasource.api.MyMoviesService
import com.diegocunha.mymoviesdata.templates.ProductionDispatchersProvider
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.scope.Scope
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataSourceModule = module {

    single { GsonBuilder().create() }

    factory {
       okHttp3()
    }

    single { retrofit(get(), get()).create(MyMoviesService::class.java) }

    factory<DispatchersProvider> { ProductionDispatchersProvider }
}

private fun Scope.okHttp3(): OkHttpClient {
    val builder = OkHttpClient.Builder()
    val level = if (BuildConfig.DEBUG) {
        HttpLoggingInterceptor.Level.BODY
    } else {
        HttpLoggingInterceptor.Level.NONE
    }

    val logger = HttpLoggingInterceptor()
    logger.level = level

    builder.interceptors().add(logger)
    return builder.build()
}

private fun Scope.retrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(CallAdapterFactory())
        .build()
}