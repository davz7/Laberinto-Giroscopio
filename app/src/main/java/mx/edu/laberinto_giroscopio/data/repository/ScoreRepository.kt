package mx.edu.laberinto_giroscopio.data.repository


import mx.edu.laberinto_giroscopio.data.model.ScoreDto
import mx.edu.laberinto_giroscopio.data.network.RetrofitClient
import retrofit2.Response

class ScoreRepository {

    suspend fun getScores(): Response<List<ScoreDto>> {
        return RetrofitClient.api.getScoresResponse()
    }

    suspend fun createScore(score: ScoreDto): Response<ScoreDto> {
        return RetrofitClient.api.createScoreResponse(score)
    }

    suspend fun updateScore(id: Long, score: ScoreDto): Response<ScoreDto> {
        return RetrofitClient.api.updateScoreResponse(id, score)
    }

    suspend fun deleteScore(id: Long): Response<Unit> {
        return RetrofitClient.api.deleteScoreResponse(id)
    }
}
