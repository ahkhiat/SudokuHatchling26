package com.devid_academy.sudokuhatchling26.logic.viewmodel

import androidx.lifecycle.ViewModel
import com.devid_academy.sudokuhatchling26.logic.data.repository.UserRepository
import com.devid_academy.sudokuhatchling26.logic.enum.AuthentificationStateEnum
import kotlinx.coroutines.flow.StateFlow

class UserViewModel(
    private val userRepository: UserRepository
): ViewModel() {
    fun observeUserState(): StateFlow<AuthentificationStateEnum> = userRepository.observeUserState()
}