package mx.edu.laberinto_giroscopio.ui.screens

import GameViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import mx.edu.laberinto_giroscopio.viewmodel.ScoreViewModel
import mx.edu.laberinto_giroscopio.viewmodel.UserViewModel
import mx.edu.laberinto_giroscopio.data.sensors.GyroscopeSensorManager
import mx.edu.laberinto_giroscopio.ui.components.*

@Composable
fun GameScreen(
    vm: GameViewModel,
    userVM: UserViewModel,
    scoreVM: ScoreViewModel,
    onBack: () -> Unit,
    onLevelCompleted: () -> Unit
) {
    val context = LocalContext.current
    val pos by vm.ballPos
    val maze by vm.maze

    val user by userVM.userState.collectAsState()
    val username = user.user?.username ?: "Jugador"

    var completed by remember { mutableStateOf(false) }

    val gyro = remember {
        GyroscopeSensorManager(
            context,
            onRotation = { x, y, _ -> vm.move(x, y) },
            onUnavailable = {  }
        )
    }

    DisposableEffect(Unit) {
        gyro.start()
        onDispose { gyro.stop() }
    }

    LaunchedEffect(pos, completed) {
        if (!completed) {
            val col = ((pos.x + 22.5f) / 45f).toInt()
            val row = ((pos.y + 22.5f) / 45f).toInt()

            if (row in maze.indices && col in maze[row].indices && maze[row][col] == 3) {
                completed = true
                onLevelCompleted()
            }
        }
    }

    AppBackground {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppTopBar(title = "Juego", onBack = onBack)

            Spacer(Modifier.height(10.dp))
            Text("Jugador: $username", color = Color.White)
            Spacer(Modifier.height(10.dp))

            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                if (maze.isNotEmpty() && maze[0].isNotEmpty()) {
                    MazeContainer(
                        rows = maze.size,
                        cols = maze[0].size,
                        cellSize = 45
                    ) {
                        Box {
                            maze.forEachIndexed { r, row ->
                                row.forEachIndexed { c, cell ->
                                    Box(Modifier.offset((c * 45).dp, (r * 45).dp)) {
                                        MazeCell(type = cell)
                                    }
                                }
                            }

                            Box(Modifier.offset(pos.x.dp, pos.y.dp)) {
                                MazeBall()
                            }
                        }
                    }
                }
            }
            Spacer(Modifier.height(30.dp))
        }
    }
}
