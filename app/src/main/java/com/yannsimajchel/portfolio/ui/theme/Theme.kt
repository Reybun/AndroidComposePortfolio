package com.yannsimajchel.portfolio.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorPalette = lightColors(
    primary = lightBlue,
    primaryVariant = darkBlue,
    //secondary = Teal200,
    background = lightGrey,
    surface = Color.White,
    onPrimary = Color.White,
    //onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = darkGrey,
)

private val DarkColorPalette = darkColors(
    primary = lightBlue,
    primaryVariant = darkBlue,
    //secondary = Teal200,
    background = Color.White,
    surface = darkGrey,
    onPrimary = Color.White,
    //onSecondary = Color.Black,
    onBackground = Color.White,
    onSurface = Color.White,
)

@Composable
fun PortfolioTheme(isDarkMode: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (isDarkMode) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}