package mx.edu.laberinto_giroscopio.ui.state

import mx.edu.laberinto_giroscopio.data.model.UserDto
data class UserUiState(
    val loading: Boolean = false,
    val user: UserDto? = null,
    val error: String? = null
)
