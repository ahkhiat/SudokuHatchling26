package com.devid_academy.sudokuhatchling26.logic.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class GridSupabaseDTO(
    val id: Int,
    val puzzle: List<List<Int>>,
    val solution: List<List<Int>>,
    val difficulty: String
)
