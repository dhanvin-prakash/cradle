package com.example.cradle.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialExpressiveTheme
import androidx.compose.material3.MotionScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = InkPinkLight,
    onPrimary = Color.White,
    primaryContainer = BabyPinkLight,
    onPrimaryContainer = Color(0xFF5C1033),
    secondary = InkBlueLight,
    onSecondary = Color.White,
    secondaryContainer = BabyBlueLight,
    onSecondaryContainer = Color(0xFF0A2E47),
    tertiary = Color(0xFF6A5A8E),
    tertiaryContainer = Color(0xFFE6DDFF),
    onTertiaryContainer = Color(0xFF241A45),
    background = MistLight,
    onBackground = Color(0xFF1F1A1D),
    surface = MistLight,
    onSurface = Color(0xFF1F1A1D),
    onSurfaceVariant = Color(0xFF524349),
    surfaceContainerLowest = Color.White,
    surfaceContainerLow = Color(0xFFFFF0F5),
    surfaceContainer = Color(0xFFFBE9F0),
    surfaceContainerHigh = Color(0xFFF5E3EA),
    surfaceContainerHighest = Color(0xFFEFDDE4),
    outlineVariant = Color(0xFFD8C1C9),
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFFC2366F),
    onPrimary = Color.White,
    primaryContainer = BabyPinkDark,
    onPrimaryContainer = Color.White,
    secondary = InkBlueDark,
    onSecondary = Color(0xFF0A2E47),
    secondaryContainer = BabyBlueDark,
    onSecondaryContainer = Color.White,
    tertiary = Color(0xFFCFBDFE),
    tertiaryContainer = Color(0xFF514275),
    onTertiaryContainer = Color.White,
    background = Color(0xFF1B1218),
    onBackground = Color.White,
    surface = Color(0xFF1B1218),
    onSurface = Color.White,
    onSurfaceVariant = Color(0xFFDCDDE3),
    surfaceContainerLowest = Color(0xFF140D12),
    surfaceContainerLow = Color(0xFF22171E),
    surfaceContainer = Color(0xFF281C24),
    surfaceContainerHigh = Color(0xFF33252E),
    surfaceContainerHighest = Color(0xFF3E2E38),
    outlineVariant = Color(0xFF51434A),
)

/** Brand colours are used deliberately instead of dynamic colour so the app always feels like the hospital. */
@Composable
fun CradleTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    MaterialExpressiveTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        motionScheme = MotionScheme.expressive(),
        typography = CradleTypography,
        content = content,
    )
}
