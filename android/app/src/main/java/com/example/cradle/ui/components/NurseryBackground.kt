package com.example.cradle.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush

/** Calm vertical wash: pink at the top, white through the middle, a gentle blue at the bottom. */
@Composable
fun NurseryBackground(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val colors = MaterialTheme.colorScheme
    val brush = Brush.verticalGradient(
        0f to colors.primaryContainer,
        0.25f to colors.primaryContainer.copy(alpha = 0.7f),
        0.6f to colors.background,
        1f to colors.secondaryContainer.copy(alpha = 0.6f),
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(colors.background)
            .background(brush),
    ) {
        // Transparent containers can't derive a text colour, so set it here once for the whole app.
        CompositionLocalProvider(LocalContentColor provides colors.onBackground) {
            content()
        }
    }
}
