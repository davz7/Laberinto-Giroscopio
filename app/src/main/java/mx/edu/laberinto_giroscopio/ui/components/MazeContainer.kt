package mx.edu.laberinto_giroscopio.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MazeContainer(
    rows: Int,
    cols: Int,
    cellSize: Int = 45,   // ANTES: 60
    content: @Composable () -> Unit
) {
    val width = cols * cellSize
    val height = rows * cellSize

    Box(
        modifier = Modifier
            .width(width.dp)
            .height(height.dp)
            .background(
                Color.White.copy(alpha = 0.22f),   // antes 0.12 → más visible
                RoundedCornerShape(20.dp)
            )
            .padding(6.dp)   // antes 4dp
    ) {
        content()
    }
}

