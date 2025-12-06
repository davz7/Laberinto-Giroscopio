package mx.edu.laberinto_giroscopio.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mx.edu.laberinto_giroscopio.data.model.ScoreDto
import mx.edu.laberinto_giroscopio.data.repository.ScoreRepository

class ScoreViewModel : ViewModel() {
    private val repo = ScoreRepository()
    private val _scores = MutableStateFlow<List<ScoreDto>>(emptyList())
    val scores: StateFlow<List<ScoreDto>> = _scores
    fun loadScores() {
        viewModelScope.launch {
            _scores.value = repo.getScores()
        }
    }
    fun addScore(userId: Long, score: Int) {
        viewModelScope.launch {
            repo.createScore(ScoreDto(userId = userId, score = score))
            loadScores()
        }
    }
}
