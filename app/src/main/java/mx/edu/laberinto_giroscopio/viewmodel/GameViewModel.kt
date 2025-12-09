import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import mx.edu.laberinto_giroscopio.data.model.BallPosition
import mx.edu.laberinto_giroscopio.data.model.Levels
import mx.edu.laberinto_giroscopio.viewmodel.ScoreViewModel

class GameViewModel(private val scoreViewModel: ScoreViewModel) : ViewModel() {

    private var levelIndex = 0

    private val _maze = mutableStateOf(Levels.allLevels[levelIndex])
    val maze = _maze

    // Usamos BallPosition para almacenar la posición de la bola
    private val _ballPos = mutableStateOf(BallPosition(60f, 60f))  // Inicializa en (60, 60)
    val ballPos = _ballPos

    var currentScore: Int = 0
        private set

    // Función para mover la bola
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

        // Verificación de límites
        if (row in mazeGrid.indices && col in mazeGrid[0].indices) {
            val cell = mazeGrid[row][col]
            if (cell == 1) {
                return
            }
        }

        // Actualizar la posición de la bola
        val maxX = mazeGrid[0].size * cellSize
        val maxY = mazeGrid.size * cellSize

        if (newX in 0f..maxX && newY in 0f..maxY) {
            _ballPos.value =
                BallPosition(newX, newY)  // Aquí se actualiza la posición correctamente
            currentScore += 1

            // Actualizar la puntuación en ScoreViewModel
            scoreViewModel.updateScore(currentScore)
        }
    }

    // Función para cargar el siguiente nivel
    fun loadNextLevel() {
        if (levelIndex < Levels.allLevels.lastIndex) {
            levelIndex++
        }

        _maze.value = Levels.allLevels[levelIndex]
        resetBall()
    }

    private fun resetBall() {
        _ballPos.value = BallPosition(60f, 60f)  // Reiniciar la posición de la bola
        currentScore = 0
    }
}
