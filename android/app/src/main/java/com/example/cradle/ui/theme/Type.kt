package com.example.cradle.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontVariation
import androidx.compose.ui.text.font.FontWeight
import com.example.cradle.R

/** Google Sans Flex variable typeface, matching NeoBuddy. */
private fun googleSansFlex(weight: Int, width: Float = 100f) = Font(
    R.font.google_sans_flex,
    FontWeight(weight),
    variationSettings = FontVariation.Settings(
        FontVariation.weight(weight),
        FontVariation.width(width),
    ),
)

val GoogleSansFlex = FontFamily(listOf(300, 400, 500, 600, 700, 800, 900).map { googleSansFlex(it) })

/** Wide, extra-bold cut used for screen titles – the same "clock" style as NeoBuddy. */
val TitleFont = FontFamily(googleSansFlex(800, width = 125f))

private fun TextStyle.flex() = copy(fontFamily = GoogleSansFlex)

private val base = Typography()

val CradleTypography = Typography(
    displayLarge = base.displayLarge.flex(),
    displayMedium = base.displayMedium.flex(),
    displaySmall = base.displaySmall.flex(),
    headlineLarge = base.headlineLarge.flex(),
    headlineMedium = base.headlineMedium.flex(),
    headlineSmall = base.headlineSmall.flex(),
    titleLarge = base.titleLarge.flex(),
    titleMedium = base.titleMedium.flex(),
    titleSmall = base.titleSmall.flex(),
    bodyLarge = base.bodyLarge.flex(),
    bodyMedium = base.bodyMedium.flex(),
    bodySmall = base.bodySmall.flex(),
    labelLarge = base.labelLarge.flex(),
    labelMedium = base.labelMedium.flex(),
    labelSmall = base.labelSmall.flex(),
    displayLargeEmphasized = base.displayLargeEmphasized.flex(),
    displayMediumEmphasized = base.displayMediumEmphasized.flex(),
    displaySmallEmphasized = base.displaySmallEmphasized.flex(),
    headlineLargeEmphasized = base.headlineLargeEmphasized.flex(),
    headlineMediumEmphasized = base.headlineMediumEmphasized.flex(),
    headlineSmallEmphasized = base.headlineSmallEmphasized.flex(),
    titleLargeEmphasized = base.titleLargeEmphasized.flex(),
    titleMediumEmphasized = base.titleMediumEmphasized.flex(),
    titleSmallEmphasized = base.titleSmallEmphasized.flex(),
    bodyLargeEmphasized = base.bodyLargeEmphasized.flex(),
    bodyMediumEmphasized = base.bodyMediumEmphasized.flex(),
    bodySmallEmphasized = base.bodySmallEmphasized.flex(),
    labelLargeEmphasized = base.labelLargeEmphasized.flex(),
    labelMediumEmphasized = base.labelMediumEmphasized.flex(),
    labelSmallEmphasized = base.labelSmallEmphasized.flex(),
)
