package com.devid_academy.sudokuhatchling26.data.manager

import android.content.SharedPreferences
import com.devid_academy.sudokuhatchling26.TOKEN
import com.devid_academy.sudokuhatchling26.data.network.SupabaseModule

class PreferencesManager(
    private val sharedPreferences: SharedPreferences,
    private val supabaseModule: SupabaseModule
    ) {

    fun getToken(): String? {
        return sharedPreferences.getString(TOKEN, null)
    }
    fun setToken(token: String) {
        sharedPreferences.edit().putString(TOKEN, token).apply()
    }
    fun removeToken() {
        sharedPreferences.edit().remove(TOKEN).apply()
    }

}