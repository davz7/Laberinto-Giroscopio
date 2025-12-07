package mx.edu.laberinto_giroscopio.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Alignment

@Composable
fun AppBackground(content: @Composable () -> Unit) {
    val gradient = Brush.verticalGradient(
        listOf(
            Color(0xFF7F53AC),
            Color(0xFF647DEE)
        )
    )

    Box(
        Modifier
            .fillMaxSize()
            .background(gradient),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}
