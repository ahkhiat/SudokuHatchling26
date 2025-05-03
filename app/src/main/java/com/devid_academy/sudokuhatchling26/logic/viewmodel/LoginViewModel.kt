package com.devid_academy.sudokuhatchling26.logic.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devid_academy.sudokuhatchling26.logic.data.repository.UserRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val userRepository: UserRepository,
): ViewModel() {

    private val _loginSharedFlow = MutableSharedFlow<AuthEvent?>()
    val loginSharedFlow: SharedFlow<AuthEvent?> = _loginSharedFlow

    fun loginUser(
        email: String,
        password: String
    ) {
        val cleanedEmail = email.trim()
        val cleanedPassword = password.trim()
        viewModelScope.launch {
            try {
                userRepository.loginUser(
                    cleanedEmail,
                    cleanedPassword
                )

//                val currentUser = userRepository.getCurrentUser()
//                Log.i("LOGIN VM", "currentUser = $currentUser")

//                val currentSession = userRepository.getUserSession()
//                Log.i("LOGIN VM", "currentSession = $currentSession")

            } catch (e: Exception) {
                Log.e("LOGIN VM", "Erreur lors du login ou de la récupération : ${e.message}")
            }
        }
    }

}

sealed class AuthEvent {
    data object NavigateToOnBoarding: AuthEvent()
    data object NavigateToLogin: AuthEvent()
    data object NavigateToStart: AuthEvent()
    data object NavigateToChooseLevel: AuthEvent()
    data class ShowSnackBar(val resId: Int): AuthEvent()
    data object Unknown: AuthEvent()
    data object Error: AuthEvent()
}