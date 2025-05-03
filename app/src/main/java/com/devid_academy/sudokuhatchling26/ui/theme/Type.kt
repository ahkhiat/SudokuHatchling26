package com.devid_academy.sudokuhatchling26.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.devid_academy.sudokuhatchling26.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

val SummaryNotesFamily = FontFamily(
    Font(
        R.font.summary_notes,
        FontWeight.Normal
    ),
)

val PerfectPenmanshipFamily = FontFamily(
    Font(
        R.font.perfect_penmanship,
        FontWeight.Normal
    )
)
val RecoletaFamily = FontFamily(
    Font(
        R.font.recoleta,
        FontWeight.Normal
    )
)

val MontserratAlternatesFamily = FontFamily(
    Font(R.font.montserrat_alternates_regular, FontWeight.Normal),
)