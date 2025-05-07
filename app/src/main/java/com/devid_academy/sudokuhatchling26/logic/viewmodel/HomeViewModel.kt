package com.devid_academy.sudokuhatchling26.logic.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devid_academy.sudokuhatchling26.logic.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val userRepository: UserRepository
): ViewModel() {
    private val _usernameStateFlow = MutableStateFlow<String>("")
    val usernameStateFlow: StateFlow<String> = _usernameStateFlow

    init {
        getUsername()
    }

    fun getUsername() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val username = userRepository.getUsernameFromProfile()
                if (username != null) {
                    _usernameStateFlow.value = username
                }
            }
        }
    }

    fun logoutUser() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                userRepository.logoutUser()
            }
        }
    }

}