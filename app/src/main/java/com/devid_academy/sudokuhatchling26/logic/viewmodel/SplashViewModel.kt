package com.devid_academy.sudokuhatchling26.logic.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devid_academy.sudokuhatchling26.R
import com.devid_academy.sudokuhatchling26.logic.data.network.SupabaseModule
import com.devid_academy.sudokuhatchling26.ui.navigation.Screen
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.status.SessionStatus
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SplashViewModel(
    private val supabaseModule: SupabaseModule,
): ViewModel() {
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _showFirstSplash = MutableStateFlow(false)
    val showFirstSplash: StateFlow<Boolean> = _showFirstSplash

    private val _animateEggs = MutableStateFlow(false)
    val animateEggs: StateFlow<Boolean> = _animateEggs

    private val _showSecondSplash = MutableStateFlow(false)
    val showSecondSplash: StateFlow<Boolean> = _showSecondSplash

    private val _loginSharedFlow = MutableSharedFlow<AuthEvent>()
    val loginSharedFlow: SharedFlow<AuthEvent> = _loginSharedFlow

    init {
        verifyToken()
    }

    fun verifyToken() {
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
//            _goToMainOrLogin.emit(
//                if(user == null)
//                    Screen.OnBoardingOne.route
//                else
//                    Screen.ChooseLevel.route
//            )
            supabaseModule.client.auth.sessionStatus.collect {
                when (it) {
                    is SessionStatus.Authenticated ->
                        _loginSharedFlow.emit(AuthEvent.NavigateToChooseLevel)
                    is SessionStatus.NotAuthenticated ->
                        _loginSharedFlow.emit(AuthEvent.NavigateToOnBoarding)
                    is SessionStatus.Initializing ->
                        _loginSharedFlow.emit(AuthEvent.ShowSnackBar(R.string.splash_initializing))
                    is SessionStatus.RefreshFailure ->
                        _loginSharedFlow.emit(AuthEvent.ShowSnackBar(R.string.splash_refresh_failure))
                }
            }



            _isLoading.value = false
        }
    }
}