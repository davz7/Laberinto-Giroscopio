package mx.edu.laberinto_giroscopio.ui.state

data class UiState<T>(
    val loading: Boolean = false,
    val data: T? = null,
    val error: String? = null
)