package mx.edu.laberinto_giroscopio.data.repository

import mx.edu.laberinto_giroscopio.data.model.ScoreDto
import mx.edu.laberinto_giroscopio.data.network.RetrofitClient
import retrofit2.Response

class ScoreRepository {

    suspend fun getScores(): Response<List<ScoreDto>> {
        return RetrofitClient.api.getScores()
    }

    suspend fun submitScore(score: ScoreDto): Response<ScoreDto> {
        return RetrofitClient.api.createScore(score)
    }

    suspend fun updateScore(id: Long, score: ScoreDto): Response<ScoreDto> {
        return RetrofitClient.api.updateScore(id, score)
    }

    suspend fun deleteScore(id: Long): Response<Unit> {
        return RetrofitClient.api.deleteScore(id)
    }
}
