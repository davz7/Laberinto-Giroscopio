package mx.edu.laberinto_giroscopio.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import mx.edu.laberinto_giroscopio.ui.components.*

@Composable
fun HomeScreen(nav: NavController) {

    AppBackground {
        Box(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            contentAlignment = Alignment.Center
        ) {

            AppCard(modifier = Modifier.fillMaxWidth(0.85f)) {

                AppTitle("Menú principal")
                Spacer(Modifier.height(6.dp))
                AppSubtitle("Selecciona una opción")
                Spacer(Modifier.height(25.dp))

                // BOTÓN JUGAR
                AppButton(
                    text = "Jugar",
                    onClick = { nav.navigate("game") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp)
                )

                Spacer(Modifier.height(15.dp))

                // BOTÓN PUNTUACIONES
                AppOutlinedButton(
                    text = "Puntuaciones",
                    onClick = { nav.navigate("scores") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp)
                )

                Spacer(Modifier.height(15.dp))

                // BOTÓN CERRAR SESIÓN
                AppOutlinedButton(
                    text = "Cerrar sesión",
                    onClick = { nav.navigate("login") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp)
                )
            }
        }
    }
}
