package com.devid_academy.sudokuhatchling26.logic.data.repository

import com.devid_academy.sudokuhatchling26.logic.data.dto.GridDTO
import com.devid_academy.sudokuhatchling26.logic.enum.LevelChoiceEnum
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from

class GameRepository(
    private val client: SupabaseClient
) {

    suspend fun getRandomGridByDifficulty(level: LevelChoiceEnum): GridDTO? {
        return try {
            client
                .from("grid")
                .select() {
                    filter {
                        eq("difficulty", level.name)
                    }
                }
                .decodeSingle<GridDTO>()

        } catch (e: Exception) {
            null
        }
    }

}