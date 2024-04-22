package com.example.css545application.ui

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.css545application.ui.theme.CSS545ApplicationTheme



@Composable
fun UserSettings() {
    val context = LocalContext.current
    var imageURI by remember { mutableStateOf<Uri?>(null) }
    var userName by remember { mutableStateOf(TextFieldValue("")) }
    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri -> imageURI = uri }
    )

    var isImageSaved by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Pick display photo from Gallery")
        //imageDisplay(imageURI = imageURI, context = context)
        TextField(
            value = userName,
            onValueChange = {userName = it },
            label = { Text("Enter your name") }
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = {
                photoPickerLauncher.launch(
                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                )
            }, modifier = Modifier.padding(horizontal = 8.dp).width(140.dp)
            ) {
                Text(text = "Go to Gallery")
            }

            Button(onClick = {
                imageURI?.let {uri ->
                    val bitmap = imageGet(context, uri)
                    imageSave(context, uri)
                    isImageSaved = true
                }
            }, modifier = Modifier.padding(horizontal = 8.dp).width(140.dp)
            ) {
                Text(text = "Save")
            }
        }




        //if (isImageSaved) {
            loadSavedImage(imageURI = imageURI, context = context)
            Text(
                text = userName.text,
                textAlign = TextAlign.Center
            )
        //}


    }


}

@Composable
fun loadSavedImage(imageURI: Uri?, context: Context) {
    imageDisplay(imageURI = imageURI, context = context)
}

@Composable
fun imageDisplay(imageURI: Uri?, context: Context) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        Box(
            modifier = Modifier
                .size(140.dp)
                .clip(shape = CircleShape)
                .background(Color.Gray)
        ) {
            if (imageURI != null) {
                AsyncImage(
                    model = imageURI,
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(140.dp),
                    contentScale = ContentScale.Crop
                )

            } else {
                Text(
                    text = "No image selected",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 40.dp)
                )
            }
        }
    }
}

fun imageGet(context: Context, uri: Uri): Bitmap? {
    return try {
        val inputStream = context.contentResolver.openInputStream(uri)
        val bitmap = BitmapFactory.decodeStream(inputStream)
        inputStream?.close()
        bitmap
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

fun imageSave(context: Context, uri: Uri) {
    val inputStream = context.contentResolver.openInputStream(uri)
    val outputStream = context.openFileOutput("image.jpg", Context.MODE_PRIVATE)
    inputStream?.use { input ->
        outputStream.use { output ->
            input.copyTo(output)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun UserSettingsPreview() {
    CSS545ApplicationTheme {
        UserSettings()
    }
}