package com.devid_academy.sudokuhatchling26.logic.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlaysGridDTO(

    @SerialName("plays_grid_id")
    val id: Long,

    @SerialName("grid_initial")
    val gridInitial: List<List<Int>>,

    @SerialName("grid_content")
    val gridContent: List<List<Int>>,

    @SerialName("is_completed")
    val isCompleted: Boolean,

    val grid: GridSupabaseDTO
)
