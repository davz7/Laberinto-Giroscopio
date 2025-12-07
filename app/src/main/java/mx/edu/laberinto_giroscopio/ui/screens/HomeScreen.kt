package mx.edu.laberinto_giroscopio.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomeScreen(nav: NavController) {
    Column(Modifier.padding(20.dp)) {
        Button(onClick = { nav.navigate("game") }) {
            Text("Jugar")
        }
        Button(onClick = { nav.navigate("scores") }) {
            Text("Puntuaciones")
        }
        Button(onClick = { nav.navigate("login") }) {
            Text("Cerrar sesión")
        }
    }

