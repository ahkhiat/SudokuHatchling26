package com.devid_academy.sudokuhatchling26.logic.enum

import androidx.compose.ui.res.stringResource
import com.devid_academy.sudokuhatchling26.R
import kotlinx.serialization.Serializable

@Serializable
enum class LevelChoiceEnum {
    Beginner,
    Easy,
    Intermediate,
    Advanced,
    Expert,
    ;

    val levelLegend: Int
        get() = when (this) {
            Beginner -> R.string.level1_legend
            Easy -> R.string.level2_legend
            Intermediate -> R.string.level3_legend
            Advanced -> R.string.level4_legend
            Expert -> R.string.level5_legend
        }

    val levelName: Int
        get() = when(this) {
            Beginner -> R.string.level1
            Easy -> R.string.level2
            Intermediate -> R.string.level3
            Advanced -> R.string.level4
            Expert -> R.string.level5
        }

    val levelImage: Int
        get() = when(this) {
            Beginner -> R.drawable.level1
            Easy -> R.drawable.level2
            Intermediate -> R.drawable.level3
            Advanced -> R.drawable.level4
            Expert -> R.drawable.level5
        }






}