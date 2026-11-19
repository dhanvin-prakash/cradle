package com.example.cradle.ui.updates

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import com.example.cradle.ui.components.dayHeader
import java.time.LocalDate

@Composable
fun DayHeader(day: LocalDate) {
    Text(
        text = day.dayHeader(),
        style = MaterialTheme.typography.titleMediumEmphasized,
        color = MaterialTheme.colorScheme.secondary,
        modifier = Modifier.semantics { heading() },
    )
}
