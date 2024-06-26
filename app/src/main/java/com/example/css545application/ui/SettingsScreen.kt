package com.example.css545application.ui

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    val gradientColors = listOf(Color.Cyan, Color.Magenta, Color.Green)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Pick display photo and username", modifier = Modifier.padding(8.dp))
            TextField(
                value = userName,
                onValueChange = { userName = it },
                label = { Text("Enter your name") },
                modifier = Modifier.padding(8.dp)
            )
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {
                        photoPickerLauncher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    }, modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .width(140.dp)
                ) {
                    Text(text = "Go to Gallery")
                }

                Button(
                    onClick = {
                        imageURI?.let { uri ->
                            imageSave(context, uri)
                            isImageSaved = true
                            Toast.makeText(context, "User info updated", Toast.LENGTH_SHORT).show()
                        }
                    }, modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .width(140.dp)
                ) {
                    Text(text = "Save")
                }
            }
        }
        if (isImageSaved) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Column(
                    verticalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .size(200.dp, 225.dp)
                        .border(
                            border = BorderStroke(width = 2.dp, color = Color.Black)
                        )
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize().padding(16.dp),
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        LoadSavedImage(imageURI = imageURI, context = context)
                        Text(
                            text = userName.text,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            style = TextStyle(
                                fontSize = 24.sp,
                                brush = Brush.linearGradient(
                                    colors = gradientColors
                                )
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun LoadSavedImage(imageURI: Uri?, context: Context) {
    val bitmap = imageURI?.let { imageGet(context, it) }
    ImageDisplay(image = bitmap, context = context)
}

@Composable
fun ImageDisplay(image: Bitmap?, context: Context) {
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
            if (image != null) {
                Image(
                    bitmap = image.asImageBitmap(),
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