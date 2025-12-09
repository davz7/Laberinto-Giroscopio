package mx.edu.laberinto_giroscopio.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mx.edu.laberinto_giroscopio.data.model.ScoreDto
import mx.edu.laberinto_giroscopio.data.repository.ScoreRepository
import mx.edu.laberinto_giroscopio.ui.components.ScoresUiState


class ScoreViewModel : ViewModel() {

    private val repo = ScoreRepository()

    private val _uiState = MutableStateFlow(ScoresUiState())
    val uiState: StateFlow<ScoresUiState> = _uiState

    // Estado para almacenar el puntaje actual
    private val _currentScore = MutableStateFlow(0)
    val currentScore: StateFlow<Int> get() = _currentScore
    fun loadScores() {
        viewModelScope.launch {
            _uiState.value = ScoresUiState(loading = true)
            try {
                val response = repo.getScores()
                if (response.isSuccessful) {
                    _uiState.value = ScoresUiState(
                        loading = false,
                        scores = response.body() ?: emptyList()
                    )
                } else {
                    _uiState.value = ScoresUiState(error = "Error al obtener puntajes")
                }
            } catch (e: Exception) {
                _uiState.value = ScoresUiState(error = "Sin conexión al servidor")
            }
        }
    }

    fun submitScore(userId: Long, score: Int) {
        viewModelScope.launch {
            try {
                repo.submitScore(
                    ScoreDto(
                        id = null,
                        userId = userId,
                        score = score
                    )
                )
                loadScores()
            } catch (_: Exception) { }
        }
    }
    fun updateScore(score: Int) {
        _currentScore.value = score
    }
}
