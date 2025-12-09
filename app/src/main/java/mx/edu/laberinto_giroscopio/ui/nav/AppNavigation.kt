package mx.edu.laberinto_giroscopio.ui.nav

import GameViewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import mx.edu.laberinto_giroscopio.ui.screens.*
import mx.edu.laberinto_giroscopio.viewmodel.ScoreViewModel
import mx.edu.laberinto_giroscopio.viewmodel.UserViewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
@Composable
fun AppNavigation() {

    val nav = rememberNavController()
    val userVM: UserViewModel = viewModel()

    val isLoggedIn = userVM.userState.collectAsState().value.user != null

    NavHost(
        navController = nav,
        startDestination = if (isLoggedIn) "home" else "login"
    ) {

        composable("login") {
            LoginScreen(nav = nav, vm = userVM)
        }

        composable("register") {
            RegisterScreen(nav = nav, vm = userVM)
        }

        composable("home") {
            HomeScreen(nav = nav, vm = userVM)
        }

        composable("game") {
            // 1. Primero creamos el ScoreViewModel porque el GameViewModel lo necesita
            val scoreVM: ScoreViewModel = viewModel()

            // 2. Creamos el GameViewModel usando el factory para pasarle el scoreVM
            val gameVM: GameViewModel = viewModel(
                factory = viewModelFactory {
                    initializer {
                        GameViewModel(scoreVM)
                    }
                }
            )

            GameScreen(
                vm = gameVM,
                userVM = userVM,
                scoreVM = scoreVM, // Pasamos el mismo scoreVM que creamos arriba
                onBack = { nav.popBackStack() },
                onLevelCompleted = { nav.navigate("levelCompleted") }
            )
        }

        composable("scores") {
            val scoreVM: ScoreViewModel = viewModel()
            ScoresScreen(vm = scoreVM, userVM = userVM)
        }

        composable("levelCompleted") {
            // AQUI TAMBIÉN TRONABA: Necesitas hacer lo mismo si vas a crear un GameViewModel aquí

            // Nota: Al usar viewModel() aquí, estás creando una instancia NUEVA del juego.
            // Si quieres compartir el estado del juego anterior, necesitarías una solución más avanzada,
            // pero esto arreglará el error de cierre inesperado por ahora.

            val scoreVM: ScoreViewModel = viewModel()

            val gameVM: GameViewModel = viewModel(
                factory = viewModelFactory {
                    initializer {
                        GameViewModel(scoreVM)
                    }
                }
            )

            LevelCompletedScreen(nav = nav, userVM = userVM, gameVM = gameVM)
        }
    }
}