package com.diegocunha.mymoviesdata.ui.screens.home

import androidx.compose.runtime.Composable
import com.diegocunha.mymoviesdata.ui.extensions.navigateWithSharedAxisX
import com.diegocunha.mymoviesdata.ui.templates.fragment.ComposableFragment

class HomeFragment : ComposableFragment() {

    @Composable
    override fun ComposableContent() {
        HomeScreen {
            val action =
                HomeFragmentDirections.actionHomeFragmentToMovieDetailFragment(
                    it.second,
                    it.first
                )
            navigateWithSharedAxisX(action)
        }
    }
}