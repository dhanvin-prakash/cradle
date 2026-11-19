package com.example.cradle.model

import androidx.annotation.DrawableRes
import com.example.cradle.R

enum class VitalKind(
    val title: String,
    val unit: String,
    @param:DrawableRes val icon: Int,
    val fractionDigits: Int,
    /** Typical newborn range, used only to reassure parents – not for clinical decisions. */
    val typicalRange: ClosedFloatingPointRange<Double>,
    val explanation: String,
) {
    HeartRate(
        title = "Heart rate",
        unit = "bpm",
        icon = R.drawable.ic_favorite,
        fractionDigits = 0,
        typicalRange = 100.0..160.0,
        explanation = "Newborn hearts beat much faster than an adult's. It speeds up when your baby cries or feeds and slows down during sleep.",
    ),
    Oxygen(
        title = "Oxygen",
        unit = "%",
        icon = R.drawable.ic_pulmonology,
        fractionDigits = 0,
        typicalRange = 92.0..100.0,
        explanation = "This shows how much oxygen is in your baby's blood, measured by a small soft sensor usually wrapped around a hand or foot.",
    ),
    Breathing(
        title = "Breathing",
        unit = "breaths/min",
        icon = R.drawable.ic_air,
        fractionDigits = 0,
        typicalRange = 30.0..60.0,
        explanation = "Babies breathe faster than adults and sometimes pause briefly. Short pauses are normal, and the monitor watches every breath.",
    ),
    Temperature(
        title = "Temperature",
        unit = "°C",
        icon = R.drawable.ic_device_thermostat,
        fractionDigits = 1,
        typicalRange = 36.5..37.5,
        explanation = "Small babies can lose heat quickly, so the team checks their temperature often and may use a warm cot or incubator.",
    ),
    Weight(
        title = "Weight",
        unit = "kg",
        icon = R.drawable.ic_monitor_weight,
        fractionDigits = 2,
        typicalRange = 1.0..5.0,
        explanation = "It's normal for babies to lose a little weight in the first few days and then regain it. Your baby is weighed regularly.",
    );

    fun format(value: Double): String = "%.${fractionDigits}f".format(value)
}
