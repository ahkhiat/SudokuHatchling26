package com.devid_academy.sudokuhatchling26.logic.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devid_academy.sudokuhatchling26.logic.data.network.SupabaseModule
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val supabaseModule: SupabaseModule
): ViewModel() {

    fun registerUser(email: String, password: String, passwordConfirm: String) {
        val cleanedEmail = email.trim()
        val cleanedPassword = password.trim()
        val cleanedPasswordConfirm = passwordConfirm.trim()
//        viewModelScope.launch {
//            try {
//                val response = supabaseModule.supabase.auth.signUpWith(Email) {
//                    this.email = cleanedEmail
//                    this.password = cleanedPassword
//                }
//
//            } catch(e: Exception) {
//                Log.e("REGISTER VM", "Erreur Catch : ${e.message}")
//            }
//        }
// 123456


    }


}