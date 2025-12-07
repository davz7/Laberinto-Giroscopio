package mx.edu.laberinto_giroscopio.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.geometry.Offset
import androidx.lifecycle.ViewModel
import mx.edu.laberinto_giroscopio.data.model.Levels

class GameViewModel : ViewModel() {

    // Tamaño real de la celda en la UI (45dp → 45f px lógicos)
    private val tileSize = 45f
    private val ballRadius = 20f
    private val _maze = mutableStateOf(Levels.level1)
    val maze: State<Array<IntArray>> = _maze

    private val _ballPos = mutableStateOf(Offset.Zero)
    val ballPos: State<Offset> = _ballPos

    init {
        val start = findStart()
        val centerFix = (tileSize / 2f) - ballRadius

        _ballPos.value = Offset(
            x = start.first * tileSize + centerFix,
            y = start.second * tileSize + centerFix
        )
    }

    fun move(x: Float, y: Float) {
        // Ajusta la velocidad para que coincida con el nuevo tamaño
        val speed = 10f

        val nx = _ballPos.value.x + (-y * speed)
        val ny = _ballPos.value.y + (x * speed)

        if (!isWall(nx, ny)) {
            _ballPos.value = Offset(nx, ny)
        }
    }

    private fun isWall(x: Float, y: Float): Boolean {
        val col = (x / tileSize).toInt()
        val row = (y / tileSize).toInt()

        val m = _maze.value

        if (row < 0 || row >= m.size || col < 0 || col >= m[row].size)
            return true

        return m[row][col] == 1
    }

    private fun findStart(): Pair<Int, Int> {
        val m = _maze.value
        for (r in m.indices) {
            for (c in m[r].indices) {
                if (m[r][c] == 2) return c to r
            }
        }
        return 1 to 1
    }
}
