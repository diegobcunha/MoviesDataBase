package com.diegocunha.mymoviesdata.ui.screens.movie.detail

import androidx.compose.runtime.Composable
import androidx.navigation.fragment.navArgs
import com.diegocunha.mymoviesdata.ui.templates.fragment.ComposableFragment

class MovieDetailFragment: ComposableFragment() {

    private val args: MovieDetailFragmentArgs by navArgs()

    @Composable
    override fun ComposableContent() {
        MovieDetailScreen(title = args.movieTitle, id = args.movieId)
    }
}