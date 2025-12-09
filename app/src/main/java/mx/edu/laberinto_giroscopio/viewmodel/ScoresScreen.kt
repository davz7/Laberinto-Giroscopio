import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import mx.edu.laberinto_giroscopio.viewmodel.ScoreViewModel
import mx.edu.laberinto_giroscopio.viewmodel.UserViewModel

@Composable
fun ScoresScreen(vm: ScoreViewModel, userVM: UserViewModel) {
    val ui = vm.uiState.collectAsState().value
    val currentUser = userVM.userState.collectAsState().value.user?.username

    // Mostrar la puntuación actual
    Text(text = "Puntuación actual: ${vm.currentScore.collectAsState().value}", color = Color.White)

    LaunchedEffect(Unit) { vm.loadScores() }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF101010)),
        contentAlignment = Alignment.Center
    ) {
        when {
            ui.loading -> CircularProgressIndicator(color = Color.Cyan)
            ui.error != null -> Text(ui.error ?: "", color = Color.Red)
            else -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp)
                ) {
                    items(ui.scores) { score ->
                        val highlight = score.username == currentUser
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(if (highlight) Color(0xFF004D40) else Color(0xFF1E1E1E))
                                .padding(16.dp)
                        ) {
                            Row(
                                Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(score.username, color = Color.White)
                                Text(score.score.toString(), color = Color.White) // Aquí se usa score.score
                            }
                        }
                    }
                }
            }
        }
    }
}
