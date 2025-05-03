package com.devid_academy.sudokuhatchling26.logic.data.repository

import android.util.Log
import com.devid_academy.sudokuhatchling26.logic.data.dto.ProfileDTO
import com.devid_academy.sudokuhatchling26.logic.enum.AuthentificationStateEnum
import com.devid_academy.sudokuhatchling26.logic.viewmodel.AuthEvent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.auth.status.SessionSource
import io.github.jan.supabase.auth.status.SessionStatus
import io.github.jan.supabase.postgrest.from
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

    // Ne fonctionne pas
    suspend fun getUserSession() {
        try {
            val session = client.auth.currentSessionOrNull()
            Log.i("SUPABASE", "currentSession = $session")
        } catch (e: Exception) {
            Log.e("SUPABASE", "Erreur lors de la récupération de la session : ${e.message}")
        }
    }

    // Ne fonctionne pas
    suspend fun getCurrentUser() {
        val user = client.auth.retrieveUserForCurrentSession(updateSession = true)

        Log.i("SUPABASE", "currentUser = $user")
    }

    suspend fun getUsernameFromProfile(): String? {
        return try {
            val userId = client.auth.currentUserOrNull()?.id
            if (userId == null) {
                Log.e("SUPABASE", "Aucun utilisateur connecté")
                return null
            }
            val response = client
                .from("profiles")
                .select {
                    filter {
                        eq("id", userId)
                    }
                }
                .decodeSingle<ProfileDTO>()
            response.username
        } catch (e: Exception) {
            Log.e("SUPABASE", "Erreur lors de la récupération du username : ${e.message}")
            null
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
                    put("username", JsonPrimitive(username))
                }
            }
            Log.i("SUPABASE", "Supabase response = ${response}")
    }
}
