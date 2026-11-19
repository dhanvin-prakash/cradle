package com.example.cradle.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush

/** Soft baby pink → mist → baby blue wash behind every screen. */
@Composable
fun NurseryBackground(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val colors = MaterialTheme.colorScheme
    val brush = Brush.linearGradient(
        colors = listOf(
            colors.primaryContainer.copy(alpha = 0.75f),
            colors.background,
            colors.secondaryContainer.copy(alpha = 0.75f),
        ),
        start = Offset.Zero,
        end = Offset.Infinite,
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(colors.background)
            .background(brush),
    ) {
        content()
    }
}
