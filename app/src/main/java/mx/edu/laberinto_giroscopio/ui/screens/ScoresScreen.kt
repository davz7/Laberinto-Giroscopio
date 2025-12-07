package mx.edu.laberinto_giroscopio.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import mx.edu.laberinto_giroscopio.ui.components.*
import mx.edu.laberinto_giroscopio.viewmodel.ScoreViewModel

@Composable
fun ScoresScreen(vm: ScoreViewModel) {

    val state = vm.state.collectAsState().value

    LaunchedEffect(Unit) {
        vm.loadScores()
    }

    AppBackground {
        Box(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            contentAlignment = Alignment.Center
        ) {

            AppCard(modifier = Modifier.fillMaxWidth(0.90f)) {

                AppTitle("Puntuaciones")
                Spacer(Modifier.height(6.dp))
                AppSubtitle("Historial de jugadores")
                Spacer(Modifier.height(20.dp))

                when {
                    state.loading -> {
                        Column(
                            Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CircularProgressIndicator()
                            Spacer(Modifier.height(10.dp))
                            AppSubtitle("Cargando puntuaciones...")
                        }
                    }

                    state.error != null -> {
                        AppSubtitle("Ocurrió un error: ${state.error}")
                        Spacer(Modifier.height(15.dp))
                        AppButton(
                            text = "Reintentar",
                            onClick = { vm.loadScores() },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    state.data != null -> {
                        Column(
                            Modifier
                                .fillMaxWidth()
                                .verticalScroll(rememberScrollState())
                        ) {
                            state.data.forEach { score ->

                                ScoreItemCard(
                                    userId = score.userId.toString(),
                                    score = score.score,
                                    onDelete = { vm.deleteScore(score.id!!) }
                                )

                                Spacer(Modifier.height(10.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}
