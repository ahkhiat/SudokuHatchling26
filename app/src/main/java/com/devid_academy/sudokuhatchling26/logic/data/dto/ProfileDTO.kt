package com.devid_academy.sudokuhatchling26.logic.data.dto
import kotlinx.serialization.SerialName

import kotlinx.serialization.Serializable

@Serializable
data class ProfileDTO(
    val id: String,
    val username: String
)