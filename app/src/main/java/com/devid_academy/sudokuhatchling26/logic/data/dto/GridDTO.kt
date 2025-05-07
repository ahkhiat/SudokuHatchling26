package com.devid_academy.sudokuhatchling26.logic.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class GridDTO(
    val grid: List<List<Int>>
)