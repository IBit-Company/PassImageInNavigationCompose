package com.example.passimagenavigationapplicationcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.passimagenavigationapplicationcompose.ui.theme.PassImageNavigationApplicationComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PassImageNavigationApplicationComposeTheme {
                MainContent()
            }
        }
    }
}

@Composable
fun MainContent() {
    val navController = rememberNavController()

    NavHost(navController = navController , startDestination = Screens.FirstScreen.name){
        composable(Screens.FirstScreen.name){
            FirstScreen(){
                navController.navigate(Screens.SecondScreen.name)
            }
        }

        composable(Screens.SecondScreen.name){
            SecondScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PassImageNavigationApplicationComposeTheme {
        MainContent()
    }
}