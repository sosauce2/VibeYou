package app.suhasdissa.mellowmusic

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import app.suhasdissa.mellowmusic.ui.screens.home.HomeScreen
import app.suhasdissa.mellowmusic.ui.screens.home.SongsScreen
import app.suhasdissa.mellowmusic.ui.screens.settings.AboutScreen
import app.suhasdissa.mellowmusic.ui.screens.settings.SettingsScreen
import app.suhasdissa.mellowmusic.ui.search.SearchScreen

@Composable
fun AppNavHost(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = Home.route
    ) {
        composable(route = Home.route) {
            HomeScreen(onNavigate = { destination ->
                navHostController.navigateTo(destination.route)
            })
        }

        composable(route = Search.route) {
            SearchScreen()
        }

        composable(route = Settings.route) {
            SettingsScreen(onNavigate = { route ->
                navHostController.navigateTo(route)
            })
        }

        composable(route = About.route) {
            AboutScreen()
        }

        composable(Songs.route) {
            SongsScreen(showFavourites = false)

        }
        composable(FavouriteSongs.route) {
            SongsScreen(showFavourites = true)

        }
    }
}

fun NavHostController.navigateTo(route: String) = this.navigate(route) {
    launchSingleTop = true
    restoreState = true
}