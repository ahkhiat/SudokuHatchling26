package com.devid_academy.sudokuhatchling26.logic.data.dto

import com.devid_academy.sudokuhatchling26.logic.enum.LevelChoiceEnum
import kotlinx.serialization.Serializable

@Serializable
data class GridDTO(
    val id: Int,
    val puzzle: List<List<Int>>,
    val solution: List<List<Int>>,
    val difficulty: String
)
