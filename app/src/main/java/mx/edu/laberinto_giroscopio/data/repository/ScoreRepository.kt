package mx.edu.laberinto_giroscopio.data.repository


import mx.edu.laberinto_giroscopio.data.model.ScoreDto
import mx.edu.laberinto_giroscopio.data.network.RetrofitClient

class ScoreRepository {
    suspend fun getScores() = RetrofitClient.api.getScores()
    suspend fun createScore(score: ScoreDto) = RetrofitClient.api.createScore(score)
    suspend fun updateScore(id: Long, score: ScoreDto) = RetrofitClient.api.updateScore(id, score)
    suspend fun deleteScore(id: Long) = RetrofitClient.api.deleteScore(id)
}
