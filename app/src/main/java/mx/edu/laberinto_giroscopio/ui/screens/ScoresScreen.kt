package mx.edu.laberinto_giroscopio.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
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

    LaunchedEffect(Unit) { vm.loadScores() }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFFFF)),
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
                                Text(score.username ?: "", color = Color.White)
                                Text(score.score.toString(), color = Color.Cyan)
                            }
                        }

                        Spacer(Modifier.height(12.dp))
                    }
                }
            }
        }
    }
}