package mx.edu.laberinto_giroscopio.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import mx.edu.laberinto_giroscopio.ui.components.*
import mx.edu.laberinto_giroscopio.viewmodel.UserViewModel

@Composable
fun LoginScreen(nav: NavController, vm: UserViewModel) {

    var name by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    val loginState = vm.loginState.collectAsState().value

    LaunchedEffect(loginState.success) {
        if (loginState.success) {
            nav.navigate("home") {
                popUpTo("login") { inclusive = true }
            }
            vm.resetLoginState()  // Muy importante
        }
    }

    AppBackground {
        Box(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            contentAlignment = Alignment.Center
        ) {

            AppCard {

                AppTitle("Bienvenido")
                Spacer(Modifier.height(6.dp))
                AppSubtitle("Inicia sesión para continuar")
                Spacer(Modifier.height(20.dp))

                AppTextField(
                    value = name,
                    onChange = { name = it },
                    label = "Usuario",
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(15.dp))

                AppTextField(
                    value = pass,
                    onChange = { pass = it },
                    label = "Contraseña",
                    visual = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(20.dp))

                if (loginState.error != null) {
                    AppSubtitle(loginState.error ?: "")
                    Spacer(Modifier.height(10.dp))
                }

                AppButton(
                    text = if (loginState.loading) "Cargando..." else "Iniciar sesión",
                    onClick = {
                        if (!loginState.loading) vm.login(name, pass)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                )

                Spacer(Modifier.height(12.dp))

                AppOutlinedButton(
                    text = "Registrarse",
                    onClick = { nav.navigate("register") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                )
            }
        }
    }
}
