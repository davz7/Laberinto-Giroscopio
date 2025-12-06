package mx.edu.laberinto_giroscopio.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import mx.edu.laberinto_giroscopio.viewmodel.UserViewModel

@Composable
fun LoginScreen(nav: NavController, vm: UserViewModel) {
    var name by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    Column(Modifier.padding(20.dp)) {
        TextField(value = name, onValueChange = { name = it }, label = { Text("Usuario") })
        TextField(value = pass, onValueChange = { pass = it }, label = { Text("Contraseña") })
        Button(onClick = {
            vm.login(name, pass)
            if (vm.currentUser.value != null) nav.navigate("home")
        }) {
            Text("Iniciar sesión")
        }
        Button(onClick = {
            vm.register(name, pass)
            nav.navigate("home")
        }) {
            Text("Registrarse")
        }
    }
}

