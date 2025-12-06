package mx.edu.laberinto_giroscopio.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import mx.edu.laberinto_giroscopio.data.model.ScoreDto
import mx.edu.laberinto_giroscopio.data.repository.ScoreRepository
import mx.edu.laberinto_giroscopio.ui.state.UiState

class ScoreViewModel : ViewModel() {

    private val repo = ScoreRepository()

    private val _state = MutableStateFlow(UiState<List<ScoreDto>>())
    val state: StateFlow<UiState<List<ScoreDto>>> = _state

    fun loadScores() {
        _state.value = UiState(loading = true)

        viewModelScope.launch(Dispatchers.IO) {
            val response = repo.getScores()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful)
                    _state.value = UiState(data = response.body())
                else
                    _state.value = UiState(error = "Error: ${response.code()}")
            }
        }
    }

    fun addScore(userId: Long, score: Int) {
        _state.value = UiState(loading = true)

        viewModelScope.launch(Dispatchers.IO) {
            val response = repo.createScore(ScoreDto(userId = userId, score = score))
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) loadScores()
                else _state.value = UiState(error = "Error: ${response.code()}")
            }
        }
    }

    fun updateScore(id: Long, userId: Long, score: Int) {
        _state.value = UiState(loading = true)

        viewModelScope.launch(Dispatchers.IO) {
            val response = repo.updateScore(id, ScoreDto(id, userId, score))
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) loadScores()
                else _state.value = UiState(error = "Error: ${response.code()}")
            }
        }
    }

    fun deleteScore(id: Long) {
        _state.value = UiState(loading = true)

        viewModelScope.launch(Dispatchers.IO) {
            val response = repo.deleteScore(id)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) loadScores()
                else _state.value = UiState(error = "Error: ${response.code()}")
            }
        }
    }
}