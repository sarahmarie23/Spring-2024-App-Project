package com.example.css545application.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.css545application.R

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

