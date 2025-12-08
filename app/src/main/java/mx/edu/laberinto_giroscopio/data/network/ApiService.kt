package mx.edu.laberinto_giroscopio.data.network

import mx.edu.laberinto_giroscopio.data.model.ScoreDto
import mx.edu.laberinto_giroscopio.data.model.UserDto
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    // USERS
    @GET("users")
    suspend fun getUsers(): Response<List<UserDto>>

    @POST("users")
    suspend fun createUser(@Body user: UserDto): Response<UserDto>

    @PUT("users/{id}")
    suspend fun updateUser(@Path("id") id: Long, @Body user: UserDto): Response<UserDto>

    @DELETE("users/{id}")
    suspend fun deleteUser(@Path("id") id: Long): Response<Unit>

    // SCORES
    @GET("scores")
    suspend fun getScores(): Response<List<ScoreDto>>

    @POST("scores")
    suspend fun createScore(@Body score: ScoreDto): Response<ScoreDto>

    @PUT("scores/{id}")
    suspend fun updateScore(@Path("id") id: Long, @Body score: ScoreDto): Response<ScoreDto>

    @DELETE("scores/{id}")
    suspend fun deleteScore(@Path("id") id: Long): Response<Unit>
}
