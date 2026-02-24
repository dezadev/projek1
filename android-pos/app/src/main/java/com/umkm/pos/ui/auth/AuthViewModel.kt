package com.umkm.pos.ui.auth

import androidx.lifecycle.viewModelScope
import com.umkm.pos.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class AuthState(
    val isLoggedIn: Boolean = false,
    val role: String = "",
    val errorMessage: String? = null
)

class AuthViewModel : BaseViewModel() {
    private val _state = MutableStateFlow(AuthState())
    val state: StateFlow<AuthState> = _state.asStateFlow()

    fun login(email: String, pin: String) {
        viewModelScope.launch {
            _loading.value = true
            if (email.isBlank() || pin.length < 6) {
                _state.value = AuthState(errorMessage = "Email/PIN tidak valid")
            } else {
                _state.value = AuthState(isLoggedIn = true, role = "ADMIN")
            }
            _loading.value = false
        }
    }
}
