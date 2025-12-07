package mx.edu.laberinto_giroscopio.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import mx.edu.laberinto_giroscopio.data.model.UserDto
import mx.edu.laberinto_giroscopio.data.repository.UserRepository

class UserViewModel : ViewModel() {

    private val repo = UserRepository()

    private val _currentUser = MutableStateFlow<UserDto?>(null)
    val currentUser: StateFlow<UserDto?> = _currentUser

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun login(name: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {

            val response = repo.getUsers()

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val users = response.body() ?: emptyList()

                    val found = users.find { it.name == name && it.password == password }

                    if (found != null) {
                        _currentUser.value = found
                        _error.value = null
                    } else {
                        _error.value = "Usuario o contraseña incorrectos"
                    }

                } else {
                    _error.value = "Error HTTP: ${response.code()}"
                }
            }
        }
    }

    fun register(name: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {

            val response = repo.createUser(
                UserDto(name = name, password = password)
            )

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _currentUser.value = response.body()
                    _error.value = null
                } else {
                    _error.value = "Error al registrar: ${response.code()}"
                }
            }
        }
    }
}
