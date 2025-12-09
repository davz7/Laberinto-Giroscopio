package mx.edu.laberinto_giroscopio.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import mx.edu.laberinto_giroscopio.ui.components.AppButton
import mx.edu.laberinto_giroscopio.ui.components.AppOutlinedButton
import mx.edu.laberinto_giroscopio.ui.components.AppTitle
import mx.edu.laberinto_giroscopio.ui.components.AppBackground
import mx.edu.laberinto_giroscopio.viewmodel.UserViewModel

@Composable
fun HomeScreen(nav: NavController, vm: UserViewModel) {

    val userState = vm.userState.collectAsState().value
    val username = userState.user?.username ?: "Jugador"

    var showDeleteDialog by remember { mutableStateOf(false) }

    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("Eliminar Cuenta") },
            text = { Text("¿Estás seguro? Se borrará tu usuario y todos tus puntajes permanentemente.") },
            confirmButton = {
                Button(
                    onClick = {
                        showDeleteDialog = false
                        vm.deleteAccount {
                            nav.navigate("login") {
                                popUpTo("home") { inclusive = true }
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                ) {
                    Text("Eliminar", color = Color.White)
                }
            },
            dismissButton = {
                OutlinedButton(onClick = { showDeleteDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }

    AppBackground {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                AppTitle("Bienvenido, $username")
                Spacer(Modifier.height(40.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .padding(vertical = 30.dp, horizontal = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    AppButton(
                        text = "Jugar",
                        onClick = { nav.navigate("game") },
                        modifier = Modifier.fillMaxWidth().height(55.dp)
                    )

                    Spacer(Modifier.height(20.dp))

                    AppButton(
                        text = "Puntajes",
                        onClick = { nav.navigate("scores") },
                        modifier = Modifier.fillMaxWidth().height(55.dp)
                    )

                    Spacer(Modifier.height(40.dp))

                    AppOutlinedButton(
                        text = "Cerrar sesión",
                        onClick = {
                            vm.logout()
                            nav.navigate("login") {
                                popUpTo("home") { inclusive = true }
                            }
                        },
                        modifier = Modifier.fillMaxWidth(0.8f).height(55.dp)
                    )

                    Spacer(Modifier.height(15.dp))

                    Button(
                        onClick = { showDeleteDialog = true },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFFCCCC),
                            contentColor = Color.Red
                        ),
                        shape = RoundedCornerShape(15.dp),
                        modifier = Modifier.fillMaxWidth(0.8f).height(55.dp)
                    ) {
                        Text("Eliminar mi cuenta")
                    }
                }
            }
        }
    }
}