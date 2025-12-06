package mx.edu.laberinto_giroscopio.data.repository

import mx.edu.laberinto_giroscopio.data.model.UserDto

class UserRepository {
    suspend fun getUsers() = RetrofitClient.api.getUsers()
    suspend fun createUser(user: UserDto) = RetrofitClient.api.createUser(user)
    suspend fun updateUser(id: Long, user: UserDto) = RetrofitClient.api.updateUser(id, user)
    suspend fun deleteUser(id: Long) = RetrofitClient.api.deleteUser(id)
}
