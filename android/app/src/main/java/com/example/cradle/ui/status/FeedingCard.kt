package com.example.cradle.ui.status

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.cradle.R
import com.example.cradle.model.FeedingInfo
import com.example.cradle.ui.components.InfoCard
import com.example.cradle.ui.components.LabeledRow
import com.example.cradle.ui.components.relativeToNow
import com.example.cradle.ui.components.shortTime

@Composable
fun FeedingCard(feeding: FeedingInfo) {
    InfoCard(title = "Feeding", icon = R.drawable.ic_water_drop) {
        LabeledRow(R.drawable.ic_schedule, "Last feed", feeding.lastFeed.relativeToNow())
        LabeledRow(R.drawable.ic_alarm, "Next feed", feeding.nextFeed.shortTime())
        LabeledRow(R.drawable.ic_local_drink, "Amount", "${feeding.amountInMillilitres} ml")
        Text(
            feeding.method,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}
