package com.d12.companionwatchlist.navigation




import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import androidx.compose.animation.*
import androidx.compose.ui.Modifier
import com.d12.companionwatchlist.screens.AboutAppScreen
import com.d12.companionwatchlist.screens.HomeScreen
import com.d12.companionwatchlist.screens.AddToWatchlistScreen
import com.d12.companionwatchlist.screens.SettingScreen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Settings : Screen("settings")


    object AddToWatchlist : Screen("addToWatchlist")


//    object  AboutApp : Screen("aboutApp")

    object AboutApp : Screen("aboutApp/{appDesc}") {
        fun createRoute(appDesc: String) = "aboutApp/$appDesc"
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavHost(navController: NavHostController, modifier: Modifier) {


    AnimatedNavHost(
        navController,
        startDestination = Screen.Home.route,
        enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }) + fadeIn() },
        exitTransition = { slideOutHorizontally(targetOffsetX = { -1000 }) + fadeOut() },
        popEnterTransition = { slideInHorizontally(initialOffsetX = { -1000 }) + fadeIn() },
        popExitTransition = { slideOutHorizontally(targetOffsetX = { 1000 }) + fadeOut() }
    ) {
        composable(Screen.Home.route) { HomeScreen(navController) }
        composable(Screen.Settings.route) { SettingScreen(navController) }
        composable(Screen.AddToWatchlist.route) { AddToWatchlistScreen(navController) }
        composable(Screen.AboutApp.route) { backStackEntry ->
            val appDesc = backStackEntry.arguments?.getString("appDesc") ?: "Unknown"
            AboutAppScreen(navController, appDesc)
        }
    }
}