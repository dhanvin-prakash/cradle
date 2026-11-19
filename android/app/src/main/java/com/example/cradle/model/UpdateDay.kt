package com.example.cradle.model

import java.time.LocalDate

/** A day's worth of updates, newest first. */
data class UpdateDay(
    val day: LocalDate,
    val updates: List<CareUpdate>,
)
