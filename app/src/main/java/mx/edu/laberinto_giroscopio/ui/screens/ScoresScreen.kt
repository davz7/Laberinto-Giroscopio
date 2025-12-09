package mx.edu.laberinto_giroscopio.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import mx.edu.laberinto_giroscopio.ui.components.*
import mx.edu.laberinto_giroscopio.viewmodel.ScoreViewModel
import mx.edu.laberinto_giroscopio.viewmodel.UserViewModel

@Composable
fun ScoresScreen(
    nav: NavController,
    vm: ScoreViewModel,
    userVM: UserViewModel
) {

    val ui = vm.uiState.collectAsState().value
    val currentUser = userVM.userState.collectAsState().value.user?.username

    LaunchedEffect(Unit) { vm.loadScores() }

    AppBackground {

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            AppTopBar(title = "Puntuaciones", onBack = { nav.popBackStack() })

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                contentAlignment = Alignment.Center
            ) {

                when {
                    ui.loading -> CircularProgressIndicator(color = Color.White)

                    ui.error != null -> {
                        AppCard {
                            Text(ui.error ?: "Error desconocido", color = Color.Red)
                        }
                    }

                    ui.scores.isEmpty() -> {
                        AppCard(modifier = Modifier.fillMaxWidth(0.9f)) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                AppTitle("¡Sin registros!")
                                Spacer(Modifier.height(10.dp))
                                AppSubtitle("Aún no hay puntuaciones registradas.")
                                Spacer(Modifier.height(20.dp))
                                Text("Sé el primero en jugar.", fontSize = 18.sp, color = Color.Gray)
                            }
                        }
                    }

                    else -> {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(ui.scores) { score ->
                                val isMe = score.username == currentUser

                                Card(
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = RoundedCornerShape(16.dp),
                                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                                    colors = CardDefaults.cardColors(
                                        containerColor = if (isMe) Color(0xFFD1C4E9) else Color.White
                                    )
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(20.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Column {
                                            Text(
                                                text = score.username ?: "Anónimo",
                                                fontWeight = if (isMe) FontWeight.Bold else FontWeight.Normal,
                                                fontSize = 18.sp,
                                                color = Color.Black
                                            )
                                            if (isMe) {
                                                Text("(Tú)", fontSize = 12.sp, color = Color(0xFF7F53AC))
                                            }
                                        }

                                        Text(
                                            text = "${score.score} pts",
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 20.sp,
                                            color = if (isMe) Color(0xFF7F53AC) else Color(0xFF647DEE)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}