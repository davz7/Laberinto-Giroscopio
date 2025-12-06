package mx.edu.laberinto_giroscopio.data.network

import mx.edu.laberinto_giroscopio.data.model.UserDto

import mx.edu.laberinto_giroscopio.data.model.ScoreDto
import retrofit2.http.*

interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<UserDto>
    @POST("users")
    suspend fun createUser(@Body user: UserDto): UserDto
    @PUT("users/{id}")
    suspend fun updateUser(@Path("id") id: Long, @Body user: UserDto): UserDto
    @DELETE("users/{id}")
    suspend fun deleteUser(@Path("id") id: Long)
    @GET("scores")
    suspend fun getScores(): List<ScoreDto>
    @POST("scores")
    suspend fun createScore(@Body score: ScoreDto): ScoreDto
    @PUT("scores/{id}")
    suspend fun updateScore(@Path("id") id: Long, @Body score: ScoreDto): ScoreDto
    @DELETE("scores/{id}")
    suspend fun deleteScore(@Path("id") id: Long)
}