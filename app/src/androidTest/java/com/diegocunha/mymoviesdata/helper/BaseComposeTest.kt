package com.diegocunha.mymoviesdata.helper

import androidx.compose.runtime.Composable
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import com.diegocunha.mymoviesdata.ui.theme.MovieAppTheme
import org.junit.Rule

open class BaseComposeTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    fun ComposeContentTestRule.setTestContent(
        content: @Composable () -> Unit
    ) = setContent {
        MovieAppTheme {
            content()
        }
    }
}