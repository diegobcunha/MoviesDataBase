package com.diegocunha.mymoviesdata.view.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.dp
import com.diegocunha.mymoviesdata.helper.BaseComposeTest
import com.diegocunha.mymoviesdata.ui.screens.home.Movie
import com.diegocunha.mymoviesdata.ui.screens.home.MovieViewData
import kotlin.test.assertTrue
import org.junit.Test

class HomeScreenTest : BaseComposeTest() {

    @Test
    fun shouldRenderBasicMovieInformation() = with(composeTestRule) {
        val movie = MovieViewData(
            id = 1337,
            title = "Movie Name",
            subtitle = "",
            poster = "",
        )

        renderMovieContent(movie)

        onNodeWithText(movie.title, useUnmergedTree = true).assertIsDisplayed()
    }

    @Test
    fun shouldInteractWhenUserClicks() = with(composeTestRule) {
        val state = mutableStateOf(false)

        val movie = MovieViewData(
            id = 1337,
            title = "Movie Name",
            subtitle = "",
            poster = "",
        )

        renderMovieContent(movie) {
            state.value = true
        }

        val node = onNodeWithText(movie.title, useUnmergedTree = true)
        node.performClick()
        assertTrue(state.value)
    }

    @Test

    private fun ComposeContentTestRule.renderMovieContent(
        movie: MovieViewData,
        onClick: (Long) -> Unit = {}
    ) = setTestContent {
        Box(Modifier.padding(72.dp)) {
            Movie(movie, onClick)
        }
    }
}