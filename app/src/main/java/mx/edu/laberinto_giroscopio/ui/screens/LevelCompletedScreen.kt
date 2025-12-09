package mx.edu.laberinto_giroscopio.ui.screens

import GameViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import mx.edu.laberinto_giroscopio.ui.components.*
import mx.edu.laberinto_giroscopio.viewmodel.ScoreViewModel
import mx.edu.laberinto_giroscopio.viewmodel.UserViewModel

@Composable
fun LevelCompletedScreen(
    nav: NavController,
    userVM: UserViewModel,
    gameVM: GameViewModel,
    scoreVM: ScoreViewModel
) {
    val user = userVM.userState.collectAsState().value.user
    val score = gameVM.currentScore

    LaunchedEffect(Unit) {
        if (user?.id != null) {
            scoreVM.submitScore(userId = user.id, score = score)
        }
    }

    AppBackground {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            contentAlignment = Alignment.Center
        ) {

            AppCard(modifier = Modifier.fillMaxWidth(0.9f)) {

                AppTitle("¡Nivel Completado!")
                Spacer(Modifier.height(12.dp))

                AppSubtitle("Bien hecho, ${user?.username ?: "Jugador"}")
                Spacer(Modifier.height(30.dp))

                AppSubtitle("Puntuación obtenida:")
                Spacer(Modifier.height(8.dp))

                AppTitle(
                    text = "$score puntos",
                )

                Spacer(Modifier.height(40.dp))

                AppButton(
                    text = "Siguiente nivel",
                    onClick = {
                        gameVM.loadNextLevel()
                        nav.navigate("game") {
                            popUpTo("levelCompleted") { inclusive = true }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp)
                )

                Spacer(Modifier.height(12.dp))

                AppOutlinedButton(
                    text = "Volver al inicio",
                    onClick = {
                        nav.navigate("home") {
                            popUpTo("home") { inclusive = true }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp)
                )
            }
        }
    }
}
