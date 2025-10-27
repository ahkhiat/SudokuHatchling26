package com.devid_academy.sudokuhatchling26.logic.enum

import com.devid_academy.sudokuhatchling26.R

enum class OnBoardingEnum {

    Page1,
    Page2,
    Page3,
    ;

    val onBoardingTitle : Int
        get() = when(this) {
            Page1 -> R.string.onboarding1_title
            Page2 -> R.string.onboarding2_title
            Page3 -> R.string.onboarding3_title
        }

    val OnBoardingImage: Int
        get() = when(this) {
            Page1 -> 0 // Pas d'image vu que j'ai fait un Row scrollable
            Page2 -> R.drawable.onboarding2_feature
            Page3 -> R.drawable.onboarding3_feature
        }

    val OnBoardingTextLine1: Int
        get() = when(this) {
            Page1 -> R.string.onboarding1_text_line1
            Page2 -> R.string.onboarding2_text_line1
            Page3 -> R.string.onboarding3_text_line1
        }


    val paginationImage: Int
        get() = when(this) {
            Page1 -> R.drawable.onboarding1_pagination
            Page2 -> R.drawable.onboarding2_pagination
            Page3 -> R.drawable.onboarding3_pagination
        }


}