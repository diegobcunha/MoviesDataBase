package com.diegocunha.mymoviesdata.ui

import com.diegocunha.mymoviesdata.ui.screens.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {

    viewModel { HomeViewModel(get(), get()) }
}