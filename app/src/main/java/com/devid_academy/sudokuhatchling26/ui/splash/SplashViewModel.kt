package com.devid_academy.sudokuhatchling26.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devid_academy.sudokuhatchling26.data.manager.PreferencesManager
import com.devid_academy.sudokuhatchling26.data.network.SupabaseModule
import com.devid_academy.sudokuhatchling26.ui.navigation.Screen
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SplashViewModel(
    private val supabaseModule: SupabaseModule,
    private val preferencesManager: PreferencesManager
): ViewModel() {
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _showFirstSplash = MutableStateFlow(false)
    val showFirstSplash: StateFlow<Boolean> = _showFirstSplash

    private val _animateEggs = MutableStateFlow(false)
    val animateEggs: StateFlow<Boolean> = _animateEggs

    private val _showSecondSplash = MutableStateFlow(false)
    val showSecondSplash: StateFlow<Boolean> = _showSecondSplash

    private val _goToMainOrLogin = MutableSharedFlow<String?>()
    val goToMainOrLogin = _goToMainOrLogin.asSharedFlow()

    init {
        verifyToken()
    }

    fun verifyToken() {
        val token = preferencesManager.getToken()
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
            _goToMainOrLogin.emit(
                if(token.isNullOrEmpty()) {
                    preferencesManager.removeToken()
                    Screen.OnBoardingOne.route
                }
                else
                    Screen.ChooseLevel.route
            )
            _isLoading.value = false
        }
    }
}