package mx.edu.laberinto_giroscopio.viewmodel

import androidx.compose.ui.geometry.Offset
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import mx.edu.laberinto_giroscopio.data.model.Levels

class GameViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val tile = 80f

    private var levelIndex = savedStateHandle["level"] ?: 0
    private var maze = Levels.allLevels[levelIndex]

    private val _ball = MutableStateFlow(
        Offset(
            savedStateHandle["x"] ?: findStart().first * tile,
            savedStateHandle["y"] ?: findStart().second * tile
        )
    )
    val ball: StateFlow<Offset> = _ball

    private fun saveState() {
        savedStateHandle["x"] = _ball.value.x
        savedStateHandle["y"] = _ball.value.y
        savedStateHandle["level"] = levelIndex
    }

    fun move(x: Float, y: Float) {
        val nx = _ball.value.x + (-y * 15)
        val ny = _ball.value.y + (x * 15)

        if (!isWall(nx, ny)) {
            _ball.value = Offset(nx, ny)
            saveState()

            if (isGoal(nx, ny)) nextLevel()
        }
    }

    private fun nextLevel() {
        levelIndex++
        if (levelIndex >= Levels.allLevels.size) levelIndex = 0

        maze = Levels.allLevels[levelIndex]

        val start = findStart()
        _ball.value = Offset(start.first * tile, start.second * tile)
        saveState()
    }
}