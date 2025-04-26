package com.devid_academy.sudokuhatchling26.logic.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devid_academy.sudokuhatchling26.logic.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChooseLevelViewModel(
    private val userRepository: UserRepository
): ViewModel() {

    private val _chooseLevelSharedFlow = MutableSharedFlow<AuthEvent>()
    val chooseLevelSharedFlow: SharedFlow<AuthEvent> = _chooseLevelSharedFlow

    fun logoutUser() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                userRepository.logoutUser()
            }
            _chooseLevelSharedFlow.emit(AuthEvent.NavigateToLogin)
        }
    }
}