package mx.edu.laberinto_giroscopio.viewmodel


import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.compose.ui.geometry.Offset
import androidx.lifecycle.ViewModel
import mx.edu.laberinto_giroscopio.data.model.Levels

class GameViewModel : ViewModel() {
    private var currentLevelIndex = 0
    private var maze = Levels.allLevels[currentLevelIndex]
    private val _ballPos = mutableStateOf(Offset(0f, 0f))
    val ballPos: State<Offset> = _ballPos
    private val tileSize = 80f
    init {
        val start = findStart()
        _ballPos.value = Offset(start.first * tileSize, start.second * tileSize)
    }
    fun updatePos(x: Float, y: Float) {
        val nx = _ballPos.value.x + (-y * 15)
        val ny = _ballPos.value.y + (x * 15)
        if (!isWall(nx, ny)) {
            _ballPos.value = Offset(nx, ny)
            if (isGoal(nx, ny)) nextLevel()
        }
    }
    private fun isWall(x: Float, y: Float): Boolean {
        val col = (x / tileSize).toInt()
        val row = (y / tileSize).toInt()
        return maze[row][col] == 1
    }
    private fun isGoal(x: Float, y: Float): Boolean {
        val col = (x / tileSize).toInt()
        val row = (y / tileSize).toInt()
        return maze[row][col] == 3
    }
    private fun nextLevel() {
        currentLevelIndex++
        if (currentLevelIndex >= Levels.allLevels.size) {
            currentLevelIndex = 0
        }
        maze = Levels.allLevels[currentLevelIndex]
        val start = findStart()
        _ballPos.value = Offset(start.first * tileSize, start.second * tileSize)
    }
    private fun findStart(): Pair<Int, Int> {
        for (r in maze.indices) {
            for (c in maze[r].indices) {
                if (maze[r][c] == 2) return Pair(c, r)
            }
        }
        return Pair(1, 1)
    }
}
