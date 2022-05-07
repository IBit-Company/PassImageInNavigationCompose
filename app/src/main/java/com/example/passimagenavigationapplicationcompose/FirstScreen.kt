package com.example.passimagenavigationapplicationcompose

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import java.io.File
import java.io.FileOutputStream

@Composable
fun FirstScreen(
    goToNextScreen: () -> Unit
) {
    val context = LocalContext.current

    var image by remember {
        mutableStateOf(BitmapFactory.decodeResource(context.resources , R.drawable.no_image))
    }
    
    val pickImage = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()){ uri ->
        uri?.let{
            val inputStream = context.contentResolver.openInputStream(it)
            image = BitmapFactory.decodeStream(inputStream)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            bitmap = image.asImageBitmap() ,
            contentDescription = "Image" ,
            modifier = Modifier.size(300.dp)
        )
        
        Button(onClick = {
            pickImage.launch("image/*")
        }) {
            Text(text = "Select Image")
        }
        
        Button(onClick = {
            val path = context.getExternalFilesDir(null)!!.absolutePath

            val tempFile = File(path , "tempFileName.jpg")
            val fOut = FileOutputStream(tempFile)
            image.compress(Bitmap.CompressFormat.JPEG , 100 , fOut)
            fOut.close()

            goToNextScreen()
        }) {
            Text(text = "Go to next Screen")
        }

    }
    
}