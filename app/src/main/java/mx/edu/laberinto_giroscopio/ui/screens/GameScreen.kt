package mx.edu.laberinto_giroscopio.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import mx.edu.laberinto_giroscopio.viewmodel.GameViewModel
import mx.edu.laberinto_giroscopio.data.sensors.GyroscopeSensorManager
import mx.edu.laberinto_giroscopio.ui.components.*

@Composable
fun GameScreen(vm: GameViewModel, onBack: () -> Unit) {

    val context = LocalContext.current
    val pos by vm.ballPos
    val maze by vm.maze

    val gyro = remember {
        GyroscopeSensorManager(
            context,
            onRotation = { x, y, _ -> vm.move(x, y) },
            onUnavailable = { }
        )
    }

    DisposableEffect(Unit) {
        gyro.start()
        onDispose { gyro.stop() }
    }

    AppBackground {

        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            AppTopBar(title = "Juego", onBack = onBack)

            Spacer(Modifier.height(20.dp))

            // 🔥 Añadimos weight para centrar verticalmente
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                MazeContainer(
                    rows = maze.size,
                    cols = maze[0].size,
                    cellSize = 45 // tamaño más profesional
                ) {
                    Box {

                        maze.forEachIndexed { r, row ->
                            row.forEachIndexed { c, cell ->
                                Box(
                                    Modifier.offset((c * 45).dp, (r * 45).dp)
                                ) {
                                    MazeCell(type = cell)
                                }
                            }
                        }

                        Box(
                            Modifier.offset(
                                (pos.x).dp,   // Ajuste horizontal
                                (pos.y).dp    // Ajuste vertical
                            )
                        ) {
                            MazeBall()
                        }

                    }
                }
            }

            Spacer(Modifier.height(30.dp))
        }
    }
}
