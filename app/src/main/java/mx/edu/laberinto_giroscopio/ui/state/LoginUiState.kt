package mx.edu.laberinto_giroscopio.ui.state

import mx.edu.laberinto_giroscopio.data.model.UserDto

data class LoginUiState(
    val loading: Boolean = false,
    val success: Boolean = false,
    val user: UserDto? = null,
    val error: String? = null
)