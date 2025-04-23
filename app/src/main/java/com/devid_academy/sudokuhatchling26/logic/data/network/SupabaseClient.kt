package com.devid_academy.sudokuhatchling26.logic.data.network

import android.net.http.HttpEngine
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import org.koin.core.component.KoinComponent
import io.ktor.client.*
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.HttpTimeout


class SupabaseModule {

//    val client = HttpClient(Android) {
//        install(HttpTimeout) {
//            requestTimeoutMillis = 5000
//        }
//    }
//
    val supabase = createSupabaseClient(
        supabaseUrl = "https://yfuztgoyvnsawrmhlfat.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InlmdXp0Z295dm5zYXdybWhsZmF0Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDQ4MDc4ODUsImV4cCI6MjA2MDM4Mzg4NX0.HGzZIoXlLymsVqAmIgTOtCkvj-R2xd6UNdkLeAKYD4k"
    ) {
        install(Auth)
        install(Postgrest)
    }



}
