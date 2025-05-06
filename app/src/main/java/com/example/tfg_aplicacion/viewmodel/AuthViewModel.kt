package com.example.tfg_aplicacion.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tfg_aplicacion.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed interface AuthUiState {
    object Idle    : AuthUiState
    object Loading : AuthUiState
    data class Success(val user: FirebaseUser) : AuthUiState
    data class Error(val message: String)    : AuthUiState
}

class AuthViewModel(
    private val repo: AuthRepository = AuthRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow<AuthUiState>(AuthUiState.Idle)
    val uiState: StateFlow<AuthUiState> = _uiState

    private val _isRegistering = MutableStateFlow(false)
    val isRegistering: StateFlow<Boolean> = _isRegistering

    //Alterna entre modo login y registro
    fun toggleRegistering() {
        _isRegistering.value = !_isRegistering.value
        _uiState.value = AuthUiState.Idle
    }

    //Llama al repositorio para iniciar sesión
    fun login(email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = AuthUiState.Loading
            repo.login(email, password)
                .onSuccess { user ->
                    _uiState.value = AuthUiState.Success(user!!)
                }
                .onFailure { e ->
                    _uiState.value = AuthUiState.Error(e.message ?: "Error de inicio")
                }
        }
    }

    //Llama al repositorio para registrar
    fun register(username: String, email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = AuthUiState.Loading
            repo.register(username, email, password)
                .onSuccess { user ->
                    _uiState.value = AuthUiState.Success(user!!)
                }
                .onFailure { e ->
                    val msg = when (e) {
                        is FirebaseAuthUserCollisionException ->
                            "Ya existe una cuenta con este correo"
                        else ->
                            e.message ?: "Error al registrar"
                    }
                    _uiState.value = AuthUiState.Error(msg)
                }
        }
    }

    //Cierra sesión
    fun logout() {
        repo.logout()
        _uiState.value = AuthUiState.Idle
    }
}
