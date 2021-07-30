package dev.kagamirai.styrax

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.kagamirai.styrax.ui.ComposeSampleAppTheme

class StyraxActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Styrax()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Styrax() {
    ComposeSampleAppTheme {
        val (value, setter) = remember { mutableStateOf("Android") }

        Surface(color = MaterialTheme.colors.background) {
            Greeting(value) {
                setter(it)
            }
        }
    }
}

@Composable
fun Greeting(name: String, updateName: (String) -> Unit) {
    Column(
        modifier = Modifier.padding(16.dp).fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        BasicText(text = "Hello $name!", modifier = Modifier.testTag("helloContent"))
        Button(onClick = {
            updateName("Magic")
        }, modifier = Modifier.padding(top = 8.dp).testTag("magicButton")) {
            BasicText(text = "Do Magic")
        }
    }
}