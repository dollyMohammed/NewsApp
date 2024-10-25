package com.example.questionapp.Util

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.questionapp.presentabon.article_Screan.ArticleScrean
import com.example.questionapp.presentabon.news_screan.NewsScreamViewModule
import com.example.questionapp.presentabon.news_screan.NewsScrean

@Composable
fun NavGraphSetUp(
   // navController: NavController
){
    val argKey= "web_url"
    val navController= rememberNavController()
     NavHost(navController = navController, startDestination = "news_screan")  {
    composable(route= "news_screan"){
        val viewModule : NewsScreamViewModule= hiltViewModel()
        NewsScrean(state = viewModule.state, onEvent = viewModule::onEvent,
            onRedFullStoryButtonClicked = {url ->
                navController.navigate("article_screan?$argKey=$url")

            })
    }
    composable(route = "article_screan?$argKey={$argKey}",
        arguments = listOf(navArgument(name = argKey){
            type = NavType.StringType
        })
    ){backStackEntry ->
        ArticleScrean(
            url = backStackEntry.arguments?.getString(argKey),
            onBackPressed = {
                navController.navigateUp()
            }
        )


    }

}
}