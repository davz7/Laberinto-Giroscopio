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

    val scoreVM: ScoreViewModel = viewModel()

    val gameVM: GameViewModel = viewModel(
        factory = viewModelFactory {
            initializer {
                GameViewModel(scoreVM)
            }
        }
    )

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
            GameScreen(
                vm = gameVM,
                userVM = userVM,
                scoreVM = scoreVM,
                onBack = { nav.popBackStack() },
                onLevelCompleted = { nav.navigate("levelCompleted") }
            )
        }

        composable("scores") {
            ScoresScreen(vm = scoreVM, userVM = userVM)
        }

        composable("levelCompleted") {
            LevelCompletedScreen(nav = nav, userVM = userVM, gameVM = gameVM, scoreVM = scoreVM)
        }
    }
}