package com.devid_academy.sudokuhatchling26.logic.data.repository

import android.util.Log
import com.devid_academy.sudokuhatchling26.logic.data.dto.GridSupabaseDTO
import com.devid_academy.sudokuhatchling26.logic.data.dto.LeaderboardItemDTO
import com.devid_academy.sudokuhatchling26.logic.data.dto.PlaysGridDTO
import com.devid_academy.sudokuhatchling26.logic.enum.LevelChoiceEnum
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.encodeToJsonElement


class GameRepository(
    private val client: SupabaseClient
) {

    suspend fun getGridByDifficulty(level: LevelChoiceEnum): GridSupabaseDTO? {
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
                .decodeSingle<GridSupabaseDTO>()
            response
        } catch (e: Exception) {
            Log.e("GAME REPO", "Error fetching grid: ${e.message}", e)
            null
        }
    }

    suspend fun getRandomGridByDifficulty(level: LevelChoiceEnum): GridSupabaseDTO? {
        return try {
            val rpcParams = buildJsonObject {
                put("diff", JsonPrimitive(level.name))
            }
            val response = client
                .postgrest
                .rpc("get_random_grid_by_difficulty", rpcParams)
                .decodeSingle<GridSupabaseDTO>()
            response
        } catch (e: Exception) {
            Log.e("GAME REPO", "Error fetching grid getRandomGrid: ${e.message}", e)
            null
        }
    }

    suspend fun getExistingGrid(level: LevelChoiceEnum): PlaysGridDTO? {
        return try {
            val rpcParams = buildJsonObject {
                put("diff", JsonPrimitive(level.name))
            }
            val response = client
                .postgrest
                .rpc("get_incomplete_plays_grid", rpcParams)
                .decodeSingle<PlaysGridDTO>()
            response
        } catch (e: Exception) {
            Log.e("GAME REPO", "Error fetching grid getExistingGrid: ${e.message}", e)
            null
        }
    }

    suspend fun createPlaysGrid(gridId: Int, gridContent: List<List<Int>>): Int? {
        try {
            val result = client.postgrest
                .rpc(
                    function = "insert_plays_grid",
                    parameters = buildJsonObject {
                        put("p_grid_id", JsonPrimitive(gridId))
                        put("p_grid_initial", Json.encodeToJsonElement(gridContent))
                        put("p_grid_content", Json.encodeToJsonElement(gridContent))
                    },
                )
            return result.data.toInt()
        } catch(e: Exception) {
            Log.e("GAME REPO", "Error creating plays grid: ${e.message}", e)
            return null
        }
    }

    suspend fun updatePlaysGrid(playsGridId: Int, gridContent: List<List<Int>>, isCompleted: Boolean = false) {
        Log.i("GAME REPO", "Updating plays grid with ID: $playsGridId, content: $gridContent")
        try {
            client.postgrest
                .rpc(
                    function = "update_plays_grid",
                    parameters = buildJsonObject {
                        put("p_plays_grid_id", JsonPrimitive(playsGridId))
                        put("p_grid_content", Json.encodeToJsonElement(gridContent))
                        put("p_is_completed", JsonPrimitive(isCompleted))
                    },
                )
        } catch(e: Exception) {
            Log.e("GAME REPO", "Error updating plays grid: ${e.message}", e)
        }
    }

    suspend fun updateUserScore(score: Int) {
        try {
            val rpcParams = buildJsonObject {
                put("scorefromapp", JsonPrimitive(score))
            }
            val response = client
                .postgrest
                .rpc("update_user_score", rpcParams)


            Log.i("GAME REPO", "Score updated successfully! score + $score")
        } catch (e: Exception) {
            Log.e("GAME REPO", "Error updating score: ${e.message}", e)
        }
    }

    suspend fun getLeaderboard(): List<LeaderboardItemDTO>? {
        return try {
            val response = client
                .postgrest
                .rpc("get_leaderboard")
                .decodeList<LeaderboardItemDTO>()
            response
        } catch (e: Exception) {
            Log.e("GAME REPO", "Error fetching leaderboard: ${e.message}", e)
            null
        }
    }


}

