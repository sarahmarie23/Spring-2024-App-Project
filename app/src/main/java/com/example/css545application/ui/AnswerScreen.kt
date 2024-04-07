package com.example.css545application.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.css545application.JokeScreen
import com.example.css545application.R
import com.example.css545application.ui.theme.CSS545ApplicationTheme

@Composable
fun AnswerScreen(
    navController: NavController,
    answer: String,
    onNextButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        JokeText(answer)
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { 
                navController.navigate(JokeScreen.Question.name) 
            }
        ) {
            Text(text = stringResource(R.string.button2))
        }
    }
}

