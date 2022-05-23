package com.diegocunha.mymoviesdata.view.components

import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.diegocunha.mymoviesdata.helper.BaseComposeTest
import com.diegocunha.mymoviesdata.ui.components.Button
import junit.framework.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ButtonTest : BaseComposeTest() {

    @Test
    fun whenButtonIsDefault_ThenCanEmmitValue() {
        val result = mutableStateOf(false)
        composeTestRule.setContent {
            Button(onClick = { result.value = true }) {
                Text(text = "testTag")
            }
        }

        val inputNode = composeTestRule.onNodeWithText("testTag")
        inputNode.performClick()
        assert(result.value)
    }

    @Test
    fun whenButtonIsDisabled_ThenCantEmmitValue() {
        val result = mutableStateOf(false)
        composeTestRule.setContent {
            Button(enabled = false, onClick = { result.value = true }) {
                Text(text = "testTag")
            }
        }

        val inputNode = composeTestRule.onNodeWithText("testTag")
        inputNode.performClick()
        Assert.assertFalse(result.value)
    }

    @Test
    fun whenButtonIsLoading_ThenCantEmmitValue() {
        val result = mutableStateOf(false)
        composeTestRule.setContent {
            Button(loading = true, onClick = { result.value = true }) {
                Text(text = "testTag")
            }
        }

        val inputNode = composeTestRule.onNodeWithText("testTag")
        inputNode.performClick()
        Assert.assertFalse(result.value)
    }
}