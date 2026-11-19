package com.example.cradle.model

data class Vital(
    val kind: VitalKind,
    val value: Double,
) {
    val isInTypicalRange: Boolean get() = value in kind.typicalRange
    val formattedValue: String get() = kind.format(value)
}
