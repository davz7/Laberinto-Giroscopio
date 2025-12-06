package mx.edu.laberinto_giroscopio.ui.screens
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import mx.edu.laberinto_giroscopio.viewmodel.ScoreViewModel

@Composable
fun ScoresScreen(vm: ScoreViewModel) {

    val state = vm.state.collectAsState().value

    LaunchedEffect(Unit) {
        vm.loadScores()
    }

    Column(Modifier.padding(20.dp)) {

        when {
            state.loading -> {
                CircularProgressIndicator()
            }

            state.error != null -> {
                Text("Error: ${state.error}")
                Button(onClick = { vm.loadScores() }) {
                    Text("Reintentar")
                }
            }

            state.data != null -> {
                state.data.forEach {
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("User: ${it.userId} | Score: ${it.score}")

                        Row {
                            Button(onClick = { vm.deleteScore(it.id!!) }) {
                                Text("Eliminar")
                            }
                        }
                    }
                    Spacer(Modifier.height(10.dp))
                }
            }
        }
    }
}