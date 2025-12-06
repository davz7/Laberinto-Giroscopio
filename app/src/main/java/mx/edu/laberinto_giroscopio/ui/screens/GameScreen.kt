package mx.edu.laberinto_giroscopio.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import mx.edu.laberinto_giroscopio.data.model.Levels
import mx.edu.laberinto_giroscopio.data.sensors.GyroscopeSensorManager
import mx.edu.laberinto_giroscopio.viewmodel.GameViewModel

@Composable
fun GameScreen(vm: GameViewModel) {
    val context = LocalContext.current
    val pos by vm.ballPos
    val gyro = remember {
        GyroscopeSensorManager(context) { x, y, _ ->
            vm.updatePos(x, y)
        }
    }
    DisposableEffect(Unit) {
        gyro.start()
        onDispose { gyro.stop() }
    }
    Box(Modifier.fillMaxSize().background(Color.Black)) {
        Levels.allLevels[0].forEachIndexed { r, row ->
            row.forEachIndexed { c, cell ->
                val color = when(cell) {
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
            modifier = Modifier
                .offset(pos.x.dp, pos.y.dp)
                .size(40.dp)
                .background(Color.Cyan, CircleShape)
        )
    }
}