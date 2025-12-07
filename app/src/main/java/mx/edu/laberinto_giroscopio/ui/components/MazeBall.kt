package mx.edu.laberinto_giroscopio.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MazeBall() {
    Box(
        modifier = Modifier
            .size(40.dp)
            .background(
                Brush.radialGradient(
                    listOf(Color.Cyan, Color.Blue),
                    radius = 70f
                ),
                CircleShape
            )
    )
}
