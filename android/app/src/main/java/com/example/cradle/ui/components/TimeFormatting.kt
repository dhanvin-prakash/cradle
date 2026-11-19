package com.example.cradle.ui.components

import android.text.format.DateUtils
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

private val shortTime: DateTimeFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)
private val dayMonth: DateTimeFormatter = DateTimeFormatter.ofPattern("d MMM")
private val weekdayDayMonth: DateTimeFormatter = DateTimeFormatter.ofPattern("EEEE d MMM")

fun Instant.relativeToNow(): String =
    DateUtils.getRelativeTimeSpanString(
        toEpochMilli(),
        System.currentTimeMillis(),
        DateUtils.MINUTE_IN_MILLIS,
    ).toString()

fun Instant.shortTime(): String = atZone(ZoneId.systemDefault()).format(shortTime)

fun Instant.dayMonth(): String = atZone(ZoneId.systemDefault()).format(dayMonth)

fun LocalDate.dayHeader(today: LocalDate = LocalDate.now()): String = when (this) {
    today -> "Today"
    today.minusDays(1) -> "Yesterday"
    else -> format(weekdayDayMonth)
}
