package com.example.cradle.ui.visit

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.cradle.R
import com.example.cradle.model.Ward
import com.example.cradle.ui.components.InfoCard
import com.example.cradle.ui.components.LabeledRow

@Composable
fun VisitingHoursCard(ward: Ward) {
    InfoCard(
        title = "Visiting Hours",
        icon = R.drawable.ic_schedule,
        containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.6f),
    ) {
        Text(ward.displayName, style = MaterialTheme.typography.titleMediumEmphasized)
        LabeledRow(R.drawable.ic_favorite, "Parents", ward.parentHours)
        LabeledRow(R.drawable.ic_group, "Other visitors", ward.visitorHours)
        LabeledRow(R.drawable.ic_bed, "At the bedside", "${ward.visitorsAtBedside} people")
    }
}
