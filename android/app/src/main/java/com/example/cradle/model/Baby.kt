package com.example.cradle.model

import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.temporal.ChronoUnit

data class Baby(
    val id: String,
    val givenName: String,
    val familyName: String,
    val birthDate: Instant,
    val birthWeightInKilograms: Double,
    val ward: Ward,
    val bed: String,
    val status: CareStatus,
    val vitals: List<Vital>,
    val feeding: FeedingInfo,
    val nurse: String,
    val doctor: String,
    val nurseNote: String,
    val updates: List<CareUpdate>,
    val lastUpdated: Instant,
) {
    val fullName: String get() = "$givenName $familyName"
    val initials: String get() = "${givenName.first()}${familyName.first()}"

    /** Day 1 is the day of birth, matching how neonatal teams count. */
    fun dayOfLife(on: Instant = Instant.now(), zone: ZoneId = ZoneId.systemDefault()): Int {
        val start: LocalDate = birthDate.atZone(zone).toLocalDate()
        val end: LocalDate = on.atZone(zone).toLocalDate()
        return maxOf(ChronoUnit.DAYS.between(start, end), 0).toInt() + 1
    }
}
