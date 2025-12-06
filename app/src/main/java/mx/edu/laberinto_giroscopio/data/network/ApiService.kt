package mx.edu.laberinto_giroscopio.data.network

import mx.edu.laberinto_giroscopio.data.model.UserDto

import mx.edu.laberinto_giroscopio.data.model.ScoreDto
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET("users")
    suspend fun getUsersResponse(): Response<List<UserDto>>

    @POST("users")
    suspend fun createUserResponse(@Body user: UserDto): Response<UserDto>

    @PUT("users/{id}")
    suspend fun updateUserResponse(@Path("id") id: Long, @Body user: UserDto): Response<UserDto>

    @DELETE("users/{id}")
    suspend fun deleteUserResponse(@Path("id") id: Long): Response<Unit>


    @GET("scores")
    suspend fun getScoresResponse(): Response<List<ScoreDto>>

    @POST("scores")
    suspend fun createScoreResponse(@Body score: ScoreDto): Response<ScoreDto>

    @PUT("scores/{id}")
    suspend fun updateScoreResponse(@Path("id") id: Long, @Body score: ScoreDto): Response<ScoreDto>

    @DELETE("scores/{id}")
    suspend fun deleteScoreResponse(@Path("id") id: Long): Response<Unit>
}