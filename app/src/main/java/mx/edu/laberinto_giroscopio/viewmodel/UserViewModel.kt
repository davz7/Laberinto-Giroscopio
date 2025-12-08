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
import mx.edu.laberinto_giroscopio.ui.state.LoginUiState
import mx.edu.laberinto_giroscopio.ui.state.RegisterUiState
import mx.edu.laberinto_giroscopio.ui.state.UserUiState
import java.io.IOException

class UserViewModel : ViewModel() {

    private val repo = UserRepository()

    private val _loginState = MutableStateFlow(LoginUiState())
    val loginState: StateFlow<LoginUiState> = _loginState

    private val _registerState = MutableStateFlow(RegisterUiState())
    val registerState: StateFlow<RegisterUiState> = _registerState

    private val _userState = MutableStateFlow(UserUiState())
    val userState: StateFlow<UserUiState> = _userState

    fun login(username: String, password: String) {
        _loginState.value = LoginUiState(loading = true)

        viewModelScope.launch(Dispatchers.IO) {
            try {

                val response = repo.getUsers()

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val users = response.body() ?: emptyList()

                        val user = users.find {
                            it.username == username && it.password == password
                        }

                        if (user != null) {
                            _loginState.value = LoginUiState(
                                loading = false,
                                success = true,
                                user = user
                            )
                            _userState.value = UserUiState(user = user)

                        } else {
                            _loginState.value = LoginUiState(
                                loading = false,
                                error = "Usuario o contraseña incorrectos"
                            )
                        }

                    } else {
                        _loginState.value = LoginUiState(
                            loading = false,
                            error = "Error del servidor: ${response.code()}"
                        )
                    }
                }

            } catch (e: IOException) {
                withContext(Dispatchers.Main) {
                    _loginState.value = LoginUiState(
                        loading = false,
                        error = "No hay conexión a internet"
                    )
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _loginState.value = LoginUiState(
                        loading = false,
                        error = "Ocurrió un error inesperado"
                    )
                }
            }
        }
    }
    fun register(username: String, password: String) {
        _registerState.value = RegisterUiState(loading = true)

        viewModelScope.launch(Dispatchers.IO) {
            try {

                val newUser = UserDto(
                    id = null,
                    username = username,
                    password = password
                )

                val response = repo.createUser(newUser)

                withContext(Dispatchers.Main) {

                    if (response.isSuccessful) {
                        _registerState.value = RegisterUiState(
                            loading = false,
                            success = true,
                            user = response.body()
                        )

                    } else if (response.code() == 409) {
                        _registerState.value = RegisterUiState(
                            loading = false,
                            error = "El usuario ya existe"
                        )

                    } else {
                        _registerState.value = RegisterUiState(
                            loading = false,
                            error = "Error en el servidor: ${response.code()}"
                        )
                    }
                }

            } catch (e: IOException) {
                withContext(Dispatchers.Main) {
                    _registerState.value = RegisterUiState(
                        loading = false,
                        error = "No hay conexión a internet"
                    )
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _registerState.value = RegisterUiState(
                        loading = false,
                        error = "Ocurrió un error inesperado"
                    )
                }
            }
        }
    }

    fun clearLoginError() {
        _loginState.value = _loginState.value.copy(error = null)
    }

    fun clearRegisterError() {
        _registerState.value = _registerState.value.copy(error = null)
    }

    fun logout() {
        _userState.value = UserUiState(user = null)
    }

    fun resetLoginState() {
        _loginState.value = LoginUiState()
    }
}
