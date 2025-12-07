package mx.edu.laberinto_giroscopio.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MazeCell(type: Int) {

    val color = when (type) {
        1 -> Color(0xFF3A3A3A)        // muro
        2 -> Color(0xFF7CFF70)        // meta
        3 -> Color(0xFFFF6262)        // trampa
        else -> Color(0xFFE0E0E0)     // camino
    }

    Box(
        modifier = Modifier
            .size(45.dp)  // antes 60 → ahora MUCHO más profesional
            .background(color, RoundedCornerShape(6.dp))
    )
}
