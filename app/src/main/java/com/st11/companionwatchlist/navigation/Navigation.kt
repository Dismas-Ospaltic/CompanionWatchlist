package com.st11.companionwatchlist.navigation




import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import androidx.compose.animation.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.st11.companionwatchlist.screens.HomeScreen
import com.st11.companionwatchlist.screens.AddToWatchlistScreen
import com.st11.companionwatchlist.screens.SettingScreen
import org.koin.androidx.compose.getViewModel

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Settings : Screen("settings")


    object AddToWatchlist : Screen("addToWatchlist")


    object  CreditAuthor : Screen("CreditAuthor")
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

    }
}