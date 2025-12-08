package mx.edu.laberinto_giroscopio.data.repository

import mx.edu.laberinto_giroscopio.data.model.UserDto
import mx.edu.laberinto_giroscopio.data.network.RetrofitClient
import retrofit2.Response

class UserRepository {

    suspend fun getUsers(): Response<List<UserDto>> {
        return RetrofitClient.api.getUsers()
    }

    suspend fun createUser(user: UserDto): Response<UserDto> {
        return RetrofitClient.api.createUser(user)
    }

    suspend fun updateUser(id: Long, user: UserDto): Response<UserDto> {
        return RetrofitClient.api.updateUser(id, user)
    }

    suspend fun deleteUser(id: Long): Response<Unit> {
        return RetrofitClient.api.deleteUser(id)
    }
}