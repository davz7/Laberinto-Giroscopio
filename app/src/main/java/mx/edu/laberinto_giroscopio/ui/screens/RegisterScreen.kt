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
fun RegisterScreen(nav: NavController, vm: UserViewModel) {

    var user by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    var pass2 by remember { mutableStateOf("") }
    var localError by remember { mutableStateOf<String?>(null) }
    val state = vm.registerState.collectAsState().value

    AppBackground {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            contentAlignment = Alignment.Center
        ) {

            AppCard(modifier = Modifier.fillMaxWidth(0.85f)) {

                AppTitle("Crear Cuenta")
                Spacer(Modifier.height(6.dp))
                AppSubtitle("Regístrate para comenzar a jugar")
                Spacer(Modifier.height(20.dp))

                AppTextField(
                    value = user,
                    onChange = { user = it },
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
                Spacer(Modifier.height(15.dp))

                AppTextField(
                    value = pass2,
                    onChange = { pass2 = it },
                    label = "Confirmar contraseña",
                    visual = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(20.dp))

                if (localError != null) {
                    AppSubtitle(localError!!)
                    Spacer(Modifier.height(10.dp))
                }

                if (state.error != null) {
                    AppSubtitle(state.error!!)
                    Spacer(Modifier.height(10.dp))
                }

                AppButton(
                    text = if (state.loading) "Cargando..." else "Registrarse",
                    onClick = {
                        when {
                            user.isBlank() -> localError = "Ingresa un usuario"
                            pass.isBlank() -> localError = "Ingresa una contraseña"
                            pass != pass2 -> localError = "Las contraseñas no coinciden"
                            else -> {
                                localError = null
                                vm.register(user, pass)
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                )

                if (state.success) {
                    LaunchedEffect(Unit) {
                        nav.navigate("login") {
                            popUpTo("register") { inclusive = true }
                        }
                    }
                }

                Spacer(Modifier.height(12.dp))

                AppOutlinedButton(
                    text = "Volver al inicio de sesión",
                    onClick = { nav.popBackStack() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                )
            }
        }
    }
}
