package com.example.css545application.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import java.util.Objects.toString

@Composable
fun JokeText(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = toString(text),
        style = MaterialTheme.typography.headlineLarge,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(16.dp),
    )
}