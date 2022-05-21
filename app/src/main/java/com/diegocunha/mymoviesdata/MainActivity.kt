package com.diegocunha.mymoviesdata

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import com.diegocunha.mymoviesdata.ui.components.DefaultScaffoldTopBar
import com.diegocunha.mymoviesdata.ui.theme.MovieAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppTheme {
                DefaultScaffoldTopBar(
                    title = { Text(text = "Home") },
                    body = {
                        LazyColumn {
                            items(5) {
                                Text(text = "Title: $it")
                            }
                        }
                    })
            }
        }

    }
}