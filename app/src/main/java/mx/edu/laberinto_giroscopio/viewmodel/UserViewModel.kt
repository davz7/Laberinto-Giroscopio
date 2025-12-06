package mx.edu.laberinto_giroscopio.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import mx.edu.laberinto_giroscopio.data.model.UserDto
import mx.edu.laberinto_giroscopio.data.repository.UserRepository

class UserViewModel : ViewModel() {
    private val repo = UserRepository()
    private val _currentUser = MutableStateFlow<UserDto?>(null)
    val currentUser: StateFlow<UserDto?> = _currentUser
    fun login(name: String, password: String) {
        viewModelScope.launch {
            val users = repo.getUsers()
            _currentUser.value = users.find { it.name == name && it.password == password }
        }
    }
    fun register(name: String, password: String) {
        viewModelScope.launch {
            val user = repo.createUser(UserDto(name = name, password = password))
            _currentUser.value = user
        }
    }
}
}