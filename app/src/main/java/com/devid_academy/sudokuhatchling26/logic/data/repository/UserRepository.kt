package com.devid_academy.sudokuhatchling26.logic.data.repository

import com.devid_academy.sudokuhatchling26.logic.viewmodel.AuthEvent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.auth.status.SessionSource
import io.github.jan.supabase.auth.status.SessionStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

class UserRepository(
    private val client: SupabaseClient
) {

    suspend fun getUserSession(): AuthEvent {
        return try {
            val status = client.auth.sessionStatus
                .filterIsInstance<SessionStatus.Authenticated>()
                .first()
            when (status.source) {
                is SessionSource.SignIn -> AuthEvent.NavigateToChooseLevel
                is SessionSource.SignUp -> AuthEvent.NavigateToChooseLevel
                else -> AuthEvent.Unknown
            }
        } catch (e: Exception) {
            AuthEvent.Error
        }
    }


    suspend fun loginUser(email: String, password: String)
        = withContext(Dispatchers.IO) {
            val session = client.auth.signInWith(Email) {
                this.email = email
                this.password = password
            }
//            println("Connecté avec : ${session.}")

    }

    suspend fun logoutUser() = withContext(Dispatchers.IO) {
        client.auth.signOut()
    }

    fun registerUser() {

    }
}
