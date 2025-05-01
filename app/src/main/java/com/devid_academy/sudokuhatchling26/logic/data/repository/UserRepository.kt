package com.devid_academy.sudokuhatchling26.logic.data.repository

import android.util.Log
import com.devid_academy.sudokuhatchling26.logic.enum.AuthentificationStateEnum
import com.devid_academy.sudokuhatchling26.logic.viewmodel.AuthEvent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.auth.status.SessionSource
import io.github.jan.supabase.auth.status.SessionStatus
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.booleanOrNull
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.jsonPrimitive

class UserRepository(
    private val client: SupabaseClient
) {
    private val userState = MutableStateFlow(AuthentificationStateEnum.Splash)
    fun observeUserState(): StateFlow<AuthentificationStateEnum> = userState

    suspend fun checkSupabaseSession() {
        client.auth.sessionStatus.collect {
            when (it) {
                is SessionStatus.Authenticated -> userState.value =
                    AuthentificationStateEnum.Authenticated

                is SessionStatus.NotAuthenticated -> userState.value =
                    AuthentificationStateEnum.Unauthenticated

                is SessionStatus.Initializing -> userState.value = AuthentificationStateEnum.Splash
                is SessionStatus.RefreshFailure -> userState.value =
                    AuthentificationStateEnum.Unauthenticated

            }
        }
    }

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

    // Ne fonctionne pas car Supabase empeche la lecture de la table users quand on n'est pas autentifié
    suspend fun checkIfEmailExists(email: String): Boolean {
        val result = client.postgrest.rpc(
            function = "email_exists",
            parameters = buildJsonObject {
                put("p_email", JsonPrimitive(email))
            }
        )
        Log.i("SUPABASE", "Supabase RPC response = ${result.data}, type = ${result.data?.javaClass?.name}")
        return result.data as? Boolean ?: false
    }

    suspend fun loginUser(email: String, password: String)
        = withContext(Dispatchers.IO) {
            val session = client.auth.signInWith(Email) {
                this.email = email
                this.password = password
            }
//            println("Connecté avec : ${session.}")

    }

    suspend fun logoutUser()
        = withContext(Dispatchers.IO) {
            client.auth.signOut()
    }

    suspend fun registerUser(email: String, password: String, username: String)
        = withContext(Dispatchers.IO) {
            val response = client.auth.signUpWith(Email) {
                this.email = email
                this.password = password
                this.data = buildJsonObject {
                    put("username", JsonPrimitive("username"))
                }

            }
    }
}
