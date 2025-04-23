package com.devid_academy.sudokuhatchling26.logic.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devid_academy.sudokuhatchling26.logic.data.network.SupabaseModule
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.auth.status.SessionSource
import io.github.jan.supabase.auth.status.SessionStatus
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val supabaseModule: SupabaseModule,
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
                val response = supabaseModule.supabase.auth.signInWith(Email) {
                    this.email = cleanedEmail
                    this.password = cleanedPassword
                }
//                if(supabaseModule.supabase.auth.currentUserOrNull()) {
//                    _loginSharedFlow.emit(AuthEvent.NavigateToMainScreen)
//                }
                supabaseModule.supabase.auth.sessionStatus.collect {
                    when(it) {
                        is SessionStatus.Authenticated -> {
                            println("Received new authenticated session.")
                            when(it.source) {
                                SessionSource.External -> TODO()
                                is SessionSource.Refresh -> TODO()
                                is SessionSource.SignIn -> {
                                    Log.d("LOGIN VM", "User logged in")
                                }
                                is SessionSource.SignUp -> TODO()
                                SessionSource.Storage -> TODO()
                                SessionSource.Unknown -> TODO()
                                is SessionSource.UserChanged -> TODO()
                                is SessionSource.UserIdentitiesChanged -> TODO()
                                SessionSource.AnonymousSignIn -> TODO()
                            }
                        }
                        SessionStatus.Initializing -> println("Initializing")
                        is SessionStatus.RefreshFailure -> println("Refresh failure ${it.cause}") //Either a network error or a internal server error
                        is SessionStatus.NotAuthenticated -> {
                            if(it.isSignOut) {
                                println("User signed out")
                            } else {
                                println("User not signed in")
                            }
                        }
                    }
                }

            } catch(e: Exception) {
                Log.e("LOGIN VM", "Erreur Catch : ${e.message}")
            }
        }
    }

}

sealed class AuthEvent {
    data object NavigateToMainScreen: AuthEvent()
    data class ShowSnackBar(val resId: Int): AuthEvent()
}