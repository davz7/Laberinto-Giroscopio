package mx.edu.laberinto_giroscopio.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import mx.edu.laberinto_giroscopio.data.model.Levels

class GameViewModel : ViewModel() {

    private var levelIndex = 0

    private val _maze = mutableStateOf(Levels.allLevels[levelIndex])
    val maze = _maze

    private val _ballPos = mutableStateOf(BallPosition(60f, 60f))
    val ballPos = _ballPos

    var currentScore: Int = 0
        private set

    fun move(x: Float, y: Float) {

        val speed = 3f
        val newX = _ballPos.value.x + (-x * speed)
        val newY = _ballPos.value.y + (y * speed)

        val cellSize = 45f

        val centerX = newX + (cellSize / 2)
        val centerY = newY + (cellSize / 2)

        val col = (centerX / cellSize).toInt()
        val row = (centerY / cellSize).toInt()

        val mazeGrid = _maze.value

        if (row in mazeGrid.indices && col in mazeGrid[0].indices) {

            val cell = mazeGrid[row][col]

            if (cell == 1) {
                return
            }
        }

        _ballPos.value = BallPosition(newX, newY)
        currentScore += 1
    }


    fun loadNextLevel() {
        if (levelIndex < Levels.allLevels.lastIndex) {
            levelIndex++
        }

        _maze.value = Levels.allLevels[levelIndex]
        resetBall()
    }

    private fun resetBall() {
        _ballPos.value = BallPosition(60f, 60f)
        currentScore = 0
    }
}

data class BallPosition(val x: Float, val y: Float)
