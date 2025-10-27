package com.devid_academy.sudokuhatchling26.logic.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devid_academy.sudokuhatchling26.logic.data.repository.UserRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SplashViewModel(
    private val userRepository: UserRepository
): ViewModel() {
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _showFirstSplash = MutableStateFlow(false)
    val showFirstSplash: StateFlow<Boolean> = _showFirstSplash

    private val _animateEggs = MutableStateFlow(false)
    val animateEggs: StateFlow<Boolean> = _animateEggs

    private val _showSecondSplash = MutableStateFlow(false)
    val showSecondSplash: StateFlow<Boolean> = _showSecondSplash

    init {
        observeSession()
    }

    fun observeSession() {

        viewModelScope.launch {

            delay(300)
            _isLoading.value = true
            _animateEggs.value = true
            _showFirstSplash.value = true
            delay(1000)
            _animateEggs.value = false
            delay(800)
            _showFirstSplash.value = false
            _showSecondSplash.value = true
            delay(3000)

            userRepository.checkSupabaseSession()
            _isLoading.value = false
        }
    }
}