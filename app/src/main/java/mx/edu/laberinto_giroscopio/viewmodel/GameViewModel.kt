    import androidx.compose.runtime.mutableStateOf
    import androidx.lifecycle.ViewModel
    import mx.edu.laberinto_giroscopio.data.model.BallPosition
    import mx.edu.laberinto_giroscopio.data.model.Levels
    import mx.edu.laberinto_giroscopio.viewmodel.ScoreViewModel

    class GameViewModel(private val scoreViewModel: ScoreViewModel) : ViewModel() {

        private var levelIndex = 0

        private val _maze = mutableStateOf(Levels.allLevels[levelIndex])
        val maze = _maze

        private val _ballPos = mutableStateOf(BallPosition(60f, 60f))
        val ballPos = _ballPos

        var currentScore: Int = 100
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
                if (cell == 1) return // Colisión con muro
            }

            val maxX = mazeGrid[0].size * cellSize
            val maxY = mazeGrid.size * cellSize

            if (newX in 0f..maxX && newY in 0f..maxY) {
                _ballPos.value = BallPosition(newX, newY)
            }
        }

        fun loadNextLevel() {
            if (levelIndex < Levels.allLevels.lastIndex) {
                levelIndex++

                currentScore += 100
                scoreViewModel.updateScore(currentScore)
            }

            _maze.value = Levels.allLevels[levelIndex]
            resetBall()
        }

        private fun resetBall() {
            _ballPos.value = BallPosition(60f, 60f)
        }
    }