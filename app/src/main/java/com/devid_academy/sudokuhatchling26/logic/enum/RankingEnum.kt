package com.devid_academy.sudokuhatchling26.logic.enum

import androidx.annotation.DrawableRes
import com.devid_academy.sudokuhatchling26.R

//enum class Medal(val rank: Int, @DrawableRes val imageRes: Int) {
//    GOLD(1, R.drawable.medal_gold),
//    SILVER(2, R.drawable.medal_silver),
//    BRONZE(3, R.drawable.medal_bronze);
//
//    companion object {
//        fun fromRank(rank: Int): Medal? {
//            return values().find { it.rank == rank }
//        }
//    }
//}

enum class Ranking {
    GOLD,
    SILVER,
    BRONZE,
    NONE,

    ;

    val imageRes: Int?
        get() = when (this) {
            GOLD -> R.drawable.medal_gold
            SILVER -> R.drawable.medal_silver
            BRONZE -> R.drawable.medal_bronze
            NONE -> null
        }

    companion object {
        fun fromRank(rank: Int): Ranking = when (rank) {
            1 -> GOLD
            2 -> SILVER
            3 -> BRONZE
            else -> NONE
        }
    }
}
