package mx.edu.laberinto_giroscopio.ui.components

import mx.edu.laberinto_giroscopio.data.model.ScoreDto

data class ScoresUiState(
    val loading: Boolean = false,
    val scores: List<ScoreDto> = emptyList(),
    val error: String? = null
)
