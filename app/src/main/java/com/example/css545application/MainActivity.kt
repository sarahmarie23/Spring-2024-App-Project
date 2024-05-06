package com.example.css545application

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.css545application.ui.SoupScreen
import com.example.css545application.ui.SoupViewModel
import com.example.css545application.ui.UserSettings
import com.example.css545application.ui.theme.CSS545ApplicationTheme



class MainActivity : ComponentActivity() {
    private val viewModel: SoupViewModel by viewModels { SoupViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CSS545ApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SoupScreen(viewModel)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun JokePreview() {
    CSS545ApplicationTheme {
        UserSettings()
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsPreview() {
    CSS545ApplicationTheme {
        UserSettings()
    }
}