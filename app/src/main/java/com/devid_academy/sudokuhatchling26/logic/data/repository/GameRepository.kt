package com.devid_academy.sudokuhatchling26.logic.data.repository

import android.util.Log
import com.devid_academy.sudokuhatchling26.logic.data.dto.GridDTO
import com.devid_academy.sudokuhatchling26.logic.enum.LevelChoiceEnum
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns
import io.github.jan.supabase.postgrest.result.PostgrestResult
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonObject

class GameRepository(
    private val client: SupabaseClient
) {

    suspend fun getGridByDifficulty(level: LevelChoiceEnum): GridDTO? {
        return try {
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
            response
        } catch (e: Exception) {
            Log.e("GAME REPO", "Error fetching grid: ${e.message}", e)
            null
        }
    }

    suspend fun getRandomGridByDifficulty(level: LevelChoiceEnum): GridDTO? {
        return try {
            val rpcParams = buildJsonObject {
                put("diff", JsonPrimitive(level.name))
            }
            val response = client
                .postgrest
                .rpc("get_random_grid_by_difficulty", rpcParams)
                .decodeSingle<GridDTO>()
            response

        } catch (e: Exception) {
            Log.e("GAME REPO", "Error fetching grid: ${e.message}", e)
            null
        }
    }

    suspend fun storeGameInDb(gridId: Int) {


    }



}

