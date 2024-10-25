package com.example.questionapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.questionapp.Util.NavGraphSetUp
import com.example.questionapp.presentabon.news_screan.NewsScreamViewModule
import com.example.questionapp.presentabon.news_screan.NewsScrean
import com.example.questionapp.ui.theme.QuestionAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuestionAppTheme {
                val navController= rememberNavController()
                NavGraphSetUp()

               /* val viewModule: NewsScreamViewModule = hiltViewModel()
                NewsScrean(
                    state = viewModule.state,
                    onEvent = viewModule::onEvent
                )*/
                // A surface container using the 'background' color from the theme
            }
        }
    }
}

