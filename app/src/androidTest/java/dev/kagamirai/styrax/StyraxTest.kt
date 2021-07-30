package dev.kagamirai.styrax

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class StyraxTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        composeTestRule.setContent {
            Styrax()
        }
    }

    @Test
    fun helloAndroidIsWrittenByDefault() {
        composeTestRule.onNodeWithTag("helloContent").assertTextEquals("Hello Android!")
    }

    @Test
    fun onMagicButtonTapMagicIsWritten() {
        composeTestRule.onNodeWithTag("magicButton").performClick()
        composeTestRule.onNodeWithTag("helloContent").assertTextEquals("Hello Magic!")
    }
}

