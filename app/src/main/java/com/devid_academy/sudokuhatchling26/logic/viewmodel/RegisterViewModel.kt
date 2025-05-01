package com.devid_academy.sudokuhatchling26.logic.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devid_academy.sudokuhatchling26.R
import com.devid_academy.sudokuhatchling26.logic.data.network.SupabaseModule
import com.devid_academy.sudokuhatchling26.logic.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterViewModel(
    private val supabaseModule: SupabaseModule,
    private val userRepository: UserRepository,
    ): ViewModel() {
    private val _email = MutableStateFlow<String>("")
    val email: StateFlow<String> = _email

    private val _password = MutableStateFlow<String>("")
    val password: StateFlow<String> = _password

    private val _passwordConfirm = MutableStateFlow<String>("")
    val passwordConfirm: StateFlow<String> = _passwordConfirm

    private val _username = MutableStateFlow<String>("")
    val username: StateFlow<String> = _username

    private val _registerSharedFlow = MutableSharedFlow<RegisterEvent>(replay = 1)
    val registerSharedFlow: SharedFlow<RegisterEvent> = _registerSharedFlow

    fun onEmailChanged(newEmail: String) {
        _email.value = newEmail
    }
    fun onPasswordChanged(newPassword: String) {
        _password.value = newPassword
    }
    fun onPasswordConfirmChanged(newPasswordConfirm: String) {
        _passwordConfirm.value = newPasswordConfirm
    }
    fun onUsernameChange(newUsername: String) {
        _username.value = newUsername
    }

    private fun arePasswordsAreSame(): Boolean {
        return _password.value == _passwordConfirm.value
    }

    private fun emailRegex(email: String): Boolean {
        val regex = Regex("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")
        return regex.matches(email)
    }

    fun verifyInputs() {
        Log.i("REGISTER VM", "Verify Inputs")

        if (_email.value.isNotEmpty() && _password.value.isNotEmpty() && _passwordConfirm.value.isNotEmpty()) {
            if (emailRegex(_email.value)) {
                if (arePasswordsAreSame()) {
//                    viewModelScope.launch {
//                        withContext(Dispatchers.IO) {
//                            val emailExists = userRepository.checkIfEmailExists(_email.value)
//                            withContext(Dispatchers.Main) {
//                                if (emailExists) {
                                    _registerSharedFlow.tryEmit(RegisterEvent.NavigateToUserName)
//                                } else {
//                                    _registerSharedFlow.tryEmit(RegisterEvent.ShowDialog(R.string.email_already_taken))
//                                    Log.i("REGISTER VM", "Email already exists")
//                                }
//                            }
//                        }
//                    }
                } else {
                    _registerSharedFlow.tryEmit(RegisterEvent.ShowDialog(R.string.password_not_same))
                    Log.i("REGISTER VM", "Passwords are not the same")
                }
            } else {
                _registerSharedFlow.tryEmit(RegisterEvent.ShowDialog(R.string.email_not_valid))
                Log.i("REGISTER VM", "Email is not valid")
            }
        } else {
            _registerSharedFlow.tryEmit(RegisterEvent.ShowDialog(R.string.please_fill_inputs))
            Log.i("REGISTER VM", "Please fill all inputs")
        }
    }


    fun registerUser() {
        val cleanedEmail = email.value.trim()
        val cleanedPassword = password.value.trim()
        val cleanedUsername = username.value.trim()

        viewModelScope.launch {
            try {
                userRepository.registerUser(
                    cleanedEmail,
                    cleanedPassword,
                    cleanedUsername
                )
            } catch(e: Exception) {
                Log.e("REGISTER VM", "Erreur Catch : ${e.message}")
            }
        }
    }


}
sealed class RegisterEvent {
    data object NavigateToUserName: RegisterEvent()
    data class ShowDialog(val resId: Int): RegisterEvent()
}