package com.devid_academy.sudokuhatchling26.logic.data.repository

import android.util.Log
import com.devid_academy.sudokuhatchling26.logic.data.dto.GridDTO
import com.devid_academy.sudokuhatchling26.logic.enum.LevelChoiceEnum
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns

class GameRepository(
    private val client: SupabaseClient
) {

    suspend fun getRandomGridByDifficulty(level: LevelChoiceEnum): GridDTO? {
        return try {
//            Log.i("GAME REPO", "Fetching grid for difficulty: ${level.name}")

            val response = client
                .from("grid")
                .select(Columns.ALL)
                {
                    filter {
                        eq("difficulty", level.name)
                    }
                    limit(1)
                }
                .decodeSingle<GridDTO>()
//            Log.i("GAME REPO", "Grid fetched successfully: $response")
            response
        } catch (e: Exception) {
            Log.e("GAME REPO", "Error fetching grid: ${e.message}", e)
            null
        }
    }


}