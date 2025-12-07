package mx.edu.laberinto_giroscopio.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import mx.edu.laberinto_giroscopio.data.sensors.GyroscopeSensorManager
import mx.edu.laberinto_giroscopio.viewmodel.GameViewModel

@Composable
fun GameScreen(vm: GameViewModel) {

    val context = LocalContext.current

    val pos by vm.ballPos
    val maze by vm.maze

    val gyro = remember {
        GyroscopeSensorManager(
            context,
            onRotation = { x, y, _ ->
                vm.move(x, y)
            },
            onUnavailable = { }
        )
    }

    DisposableEffect(Unit) {
        gyro.start()
        onDispose { gyro.stop() }
    }

    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        maze.forEachIndexed { r, row ->
            row.forEachIndexed { c, cell ->
                val color = when (cell) {
                    1 -> Color.Gray
                    2 -> Color.Green
                    3 -> Color.Red
                    else -> Color.DarkGray
                }
                Box(
                    Modifier
                        .offset((c * 80).dp, (r * 80).dp)
                        .size(80.dp)
                        .background(color)
                )
            }
        }

        Box(
            Modifier
                .offset(pos.x.dp, pos.y.dp)
                .size(40.dp)
                .background(Color.Cyan, CircleShape)
        )
    }
}
