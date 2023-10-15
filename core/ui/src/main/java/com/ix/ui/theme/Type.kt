package com.ix.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.ix.ui.R

object Fonts {
    val body = FontFamily(Font(R.font.lora))
    val title = FontFamily(Font(R.font.montserrat))
}

// Set of Material typography styles to start with
val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = Fonts.title,
    ),
    displayMedium = TextStyle(
        fontFamily = Fonts.title,
    ),
    displaySmall = TextStyle(
        fontFamily = Fonts.title,
    ),

    headlineLarge = TextStyle(
        fontFamily = Fonts.title,
    ),
    headlineMedium = TextStyle(
        fontFamily = Fonts.title,
    ),
    headlineSmall = TextStyle(
        fontFamily = Fonts.title,
    ),

    titleLarge = TextStyle(
        fontFamily = Fonts.title,
    ),
    titleMedium = TextStyle(
        fontFamily = Fonts.title,
    ),
    titleSmall = TextStyle(
        fontFamily = Fonts.title,
    ),

    bodyLarge = TextStyle(
        fontFamily = Fonts.body,
    ),
    bodyMedium = TextStyle(
        fontFamily = Fonts.body,
    ),
    bodySmall = TextStyle(
        fontFamily = Fonts.body,
    ),

    labelLarge = TextStyle(
        fontFamily = Fonts.title,
    ),
    labelMedium = TextStyle(
        fontFamily = Fonts.title,
    ),
    labelSmall = TextStyle(
        fontFamily = Fonts.title,
    ),
)
