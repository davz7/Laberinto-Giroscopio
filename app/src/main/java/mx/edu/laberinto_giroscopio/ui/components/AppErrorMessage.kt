package mx.edu.laberinto_giroscopio.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AppErrorMessage(message: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFFD6D6), RoundedCornerShape(10.dp))
            .padding(12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {

            Icon(
                imageVector = Icons.Default.Warning,
                contentDescription = "Error",
                tint = Color(0xFFB30000),
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = message,
                fontSize = 16.sp,
                color = Color(0xFFB30000)
            )
        }
    }
}
