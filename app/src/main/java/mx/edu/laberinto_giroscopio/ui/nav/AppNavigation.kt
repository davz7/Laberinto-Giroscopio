package mx.edu.laberinto_giroscopio.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import mx.edu.laberinto_giroscopio.ui.screens.GameScreen
import mx.edu.laberinto_giroscopio.ui.screens.HomeScreen
import mx.edu.laberinto_giroscopio.ui.screens.LoginScreen
import mx.edu.laberinto_giroscopio.viewmodel.GameViewModel
import mx.edu.laberinto_giroscopio.viewmodel.ScoresScreen
import mx.edu.laberinto_giroscopio.viewmodel.UserViewModel

@Composable
fun AppNavigation() {
    val userVM = UserViewModel()
    val scoreVM = ScoreViewModel()
    val gameVM = GameViewModel()
    val nav = rememberNavController()
    NavHost(navController = nav, startDestination = "login") {
        composable("login") { LoginScreen(nav, userVM) }
        composable("home") { HomeScreen(nav) }
        composable("game") { GameScreen(gameVM) }
        composable("scores") { ScoresScreen(scoreVM) }
    }


