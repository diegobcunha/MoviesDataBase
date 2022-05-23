package com.diegocunha.mymoviesdata.view.screens.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.unit.dp
import com.diegocunha.mymoviesdata.helper.BaseComposeTest
import com.diegocunha.mymoviesdata.ui.screens.movie.detail.MovieDetailScreenBody
import com.diegocunha.mymoviesdata.ui.screens.movie.detail.MovieDetailViewData
import org.junit.Test

class MovieDetailScreenTest: BaseComposeTest() {

    @Test
    fun shouldRenderBasicMovieDetailInformation() {
        val movie = MovieDetailViewData(
            title = "Movie Name",
            overview = "",
            imageUrl = "",
        )

        composeTestRule.setTestContent {
            Box {
                Box(Modifier.padding(72.dp)) {
                    MovieDetailScreenBody(movie)
                }
            }
        }

        composeTestRule.onNodeWithText(movie.title).assertIsDisplayed()
        composeTestRule.onNodeWithText(movie.overview).assertIsDisplayed()
    }
}