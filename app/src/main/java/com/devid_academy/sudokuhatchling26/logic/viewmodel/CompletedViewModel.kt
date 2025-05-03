package com.devid_academy.sudokuhatchling26.logic.viewmodel

import androidx.lifecycle.ViewModel
import com.devid_academy.sudokuhatchling26.logic.data.repository.GameRepository
import com.devid_academy.sudokuhatchling26.logic.data.repository.UserRepository

class CompletedViewModel(
    private val gameRepository: GameRepository,
    private val userRepository: UserRepository
): ViewModel() {


}