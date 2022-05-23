package com.diegocunha.mymoviesdata.datasource

import com.diegocunha.mymoviesdata.BuildConfig
import com.diegocunha.mymoviesdata.coroutines.DispatchersProvider
import com.diegocunha.mymoviesdata.datasource.api.CallAdapterFactory
import com.diegocunha.mymoviesdata.datasource.api.MyMoviesService
import com.diegocunha.mymoviesdata.datasource.mapper.ImageMapper
import com.diegocunha.mymoviesdata.datasource.mapper.MovieMapper
import com.diegocunha.mymoviesdata.datasource.movie.MovieDetailUseCase
import com.diegocunha.mymoviesdata.datasource.movie.MoviesSource
import com.diegocunha.mymoviesdata.datasource.movie.MoviesUseCase
import com.diegocunha.mymoviesdata.templates.ProductionDispatchersProvider
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.scope.Scope
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val API_KEY = "api_key"
private const val REGION = "region"
private const val LANGUAGE = "language"

val dataSourceModule = module {

    single { GsonBuilder().create() }

    factory {
        okHttp3()
    }

    single { retrofit(get(), get()).create(MyMoviesService::class.java) }

    single { ImageMapper() }

    single { MovieMapper(get()) }

    factory<DispatchersProvider> { ProductionDispatchersProvider }

    factory { MoviesSource(get()) }

    factory { MoviesUseCase(get(), get()) }

    factory { MovieDetailUseCase(get(), get()) }
}

private fun Scope.okHttp3(): OkHttpClient {
    val builder = OkHttpClient.Builder()
    builder.addInterceptor { chain ->
        val url = chain
            .request()
            .url
            .newBuilder()
            .addQueryParameter(API_KEY, BuildConfig.API_KEY)
            .addQueryParameter(LANGUAGE, BuildConfig.DEFAULT_LANGUAGE)
            .addQueryParameter(REGION, BuildConfig.DEFAULT_REGION)
            .build()
        chain.proceed(chain.request().newBuilder().url(url).build())
    }
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