package com.diegocunha.mymoviesdata.ui.screens.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
internal fun HomeScreen() {
    Scaffold {
        LazyColumn {
            items(5) {
                Text(text = "Title: $it")
            }
        }
    }
}