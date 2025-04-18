package com.devid_academy.sudokuhatchling26.ui.register

import androidx.lifecycle.ViewModel
import com.devid_academy.sudokuhatchling26.data.network.SupabaseModule
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.postgrest.postgrest


class RegisterViewModel(
    private val supabaseModule: SupabaseModule
): ViewModel() {

//    suspend fun signUpTestUser() {
//        val auth = supabaseModule.supabase.auth
//        val response = auth.signUpWith(Email) {
//            email = "test@example.com"
//            password= "motdepasse123"
//        }
//
//
//
//    }

//    suspend fun registerUser(email: String, password: String, username: String) {
//        val auth = supabaseClient.supabase.auth
//        val postgrest = supabaseClient.supabase.postgrest["profiles"]
//
//        try {
//            val response = auth.signUpWithEmail(email, password)
//            val user = response.user
//
//            if (user != null) {
//                // Insérer le profil avec l'ID de l'utilisateur
//                val insertResponse = postgrest.insert(
//                    mapOf(
//                        "id" to user.id,
//                        "username" to username,
//                        "createdAt" to null  // ou tu peux le laisser vide si Supabase le remplit automatiquement
//                    )
//                )
//
//                println("Profil inséré: $insertResponse")
//            } else {
//                println("Erreur d'inscription: ${response.error?.message}")
//            }
//        } catch (e: Exception) {
//            println("Erreur lors de l'inscription ou l'insertion : $e")
//        }
//    }


}