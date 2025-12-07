package mx.edu.laberinto_giroscopio.ui.nav

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import mx.edu.laberinto_giroscopio.ui.screens.GameScreen
import mx.edu.laberinto_giroscopio.ui.screens.HomeScreen
import mx.edu.laberinto_giroscopio.ui.screens.LoginScreen
import mx.edu.laberinto_giroscopio.ui.screens.ScoresScreen
import mx.edu.laberinto_giroscopio.viewmodel.GameViewModel
import mx.edu.laberinto_giroscopio.viewmodel.ScoreViewModel
import mx.edu.laberinto_giroscopio.viewmodel.UserViewModel

@Composable
fun AppNavigation() {

    val nav = rememberNavController()

    val userVM: UserViewModel = viewModel()
    val scoreVM: ScoreViewModel = viewModel()
    val gameVM: GameViewModel = viewModel()

    NavHost(
        navController = nav,
        startDestination = "login"
    ) {
        composable("login") { LoginScreen(nav, userVM) }
        composable("home") { HomeScreen(nav) }
        composable(route = "game") {
            GameScreen(
                vm = gameVM,
                onBack = { nav.popBackStack() }   // regresar a la pantalla anterior
            )
        }
        composable("scores") { ScoresScreen(scoreVM) }
    }
}
