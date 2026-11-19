package com.example.cradle.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance

val ColorScheme.isDark: Boolean get() = background.luminance() < 0.5f

/**
 * Colour for headings and accent text: brand blue in light mode,
 * plain white in dark mode so every piece of text reads the same.
 */
val ColorScheme.accentText: Color get() = if (isDark) onSurface else secondary

/** Same idea for pink "watch" text. */
val ColorScheme.alertText: Color get() = if (isDark) onSurface else primary
