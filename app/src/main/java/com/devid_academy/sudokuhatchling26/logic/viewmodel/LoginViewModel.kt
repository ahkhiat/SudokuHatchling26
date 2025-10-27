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
            } catch (e: Exception) {
                Log.e("LOGIN VM", "Erreur lors du login ou de la récupération : ${e.message}")
            }
        }
    }

}
