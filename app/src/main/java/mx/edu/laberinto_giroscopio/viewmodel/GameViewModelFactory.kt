package mx.edu.laberinto_giroscopio.viewmodel

import GameViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class GameViewModelFactory(private val scoreViewModel: ScoreViewModel) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameViewModel::class.java)) {
            return GameViewModel(scoreViewModel) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
