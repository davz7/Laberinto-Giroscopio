package mx.edu.laberinto_giroscopio.ui.screens
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import mx.edu.laberinto_giroscopio.viewmodel.ScoreViewModel

@Composable
fun ScoresScreen(vm: ScoreViewModel) {
    val scores by vm.scores.collectAsState()
    LaunchedEffect(Unit) {
        vm.loadScores()
    }
    Column(Modifier.padding(20.dp)) {
        scores.forEach {
            Text("User: ${it.userId} | Score: ${it.score}")
            Spacer(Modifier.height(10.dp))
        }
    }
}