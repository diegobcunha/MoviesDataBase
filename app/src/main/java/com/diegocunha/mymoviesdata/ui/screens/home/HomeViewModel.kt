package com.diegocunha.mymoviesdata.ui.screens.home

import androidx.paging.PagingData
import com.diegocunha.mymoviesdata.coroutines.DispatchersProvider
import com.diegocunha.mymoviesdata.templates.PaginationViewModel
import kotlinx.coroutines.flow.Flow

class HomeViewModel(dispatchersProvider: DispatchersProvider) :
    PaginationViewModel<Unit>(dispatchersProvider, 10) {

    override fun fetchContent(initialPageSize: Int): Flow<PagingData<Unit>> {
        TODO("Not yet implemented")
    }
}