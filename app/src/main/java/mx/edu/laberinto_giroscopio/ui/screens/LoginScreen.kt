package mx.edu.laberinto_giroscopio.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import mx.edu.laberinto_giroscopio.ui.components.AppBackground
import mx.edu.laberinto_giroscopio.ui.components.AppButton
import mx.edu.laberinto_giroscopio.ui.components.AppCard
import mx.edu.laberinto_giroscopio.ui.components.AppOutlinedButton
import mx.edu.laberinto_giroscopio.ui.components.AppSubtitle
import mx.edu.laberinto_giroscopio.ui.components.AppTextField
import mx.edu.laberinto_giroscopio.ui.components.AppTitle
import mx.edu.laberinto_giroscopio.viewmodel.UserViewModel

@Composable
fun LoginScreen(nav: NavController, vm: UserViewModel) {
    var name by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }

    AppBackground {
        Box(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp), // margen global
            contentAlignment = Alignment.Center
        ) {

            AppCard {
            AppTitle("Bienvenido")
            Spacer(Modifier.height(6.dp))
            AppSubtitle("Inicia sesión para continuar")
            Spacer(Modifier.height(20.dp))

            AppTextField(name, { name = it }, "Usuario", Modifier.fillMaxWidth())
            Spacer(Modifier.height(15.dp))
            AppTextField(
                pass, { pass = it },
                "Contraseña",
                Modifier.fillMaxWidth(),
                visual = PasswordVisualTransformation()
            )
            Spacer(Modifier.height(30.dp))

            AppButton(
                text = "Iniciar sesión",
                onClick = {
                    vm.login(name, pass)
                    if (vm.currentUser.value != null) nav.navigate("home")
                },
                modifier = Modifier.fillMaxWidth().height(50.dp)
            )

            Spacer(Modifier.height(12.dp))

            AppOutlinedButton(
                text = "Registrarse",
                onClick = {
                    vm.register(name, pass)
                    nav.navigate("home")
                },
                modifier = Modifier.fillMaxWidth().height(50.dp)
            )
            }
        }
    }
}
