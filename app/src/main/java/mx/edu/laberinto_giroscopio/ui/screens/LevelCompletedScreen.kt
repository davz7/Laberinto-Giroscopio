package mx.edu.laberinto_giroscopio.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import mx.edu.laberinto_giroscopio.ui.components.*
import mx.edu.laberinto_giroscopio.viewmodel.UserViewModel
import mx.edu.laberinto_giroscopio.viewmodel.GameViewModel

@Composable
fun LevelCompletedScreen(
    nav: NavController,
    userVM: UserViewModel,
    gameVM: GameViewModel
) {
    val user = userVM.userState.collectAsState().value.user
    val username = user?.username ?: "Jugador"
    val score = gameVM.currentScore

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

                AppSubtitle("Bien hecho, $username")
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
