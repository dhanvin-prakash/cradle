package com.example.cradle.ui.status

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.cradle.R
import com.example.cradle.model.Vital
import com.example.cradle.ui.components.SectionHeader
import com.example.cradle.ui.theme.Dimens

@Composable
fun VitalsSection(
    vitals: List<Vital>,
    onSelect: (Vital) -> Unit,
) {
    Column(verticalArrangement = Arrangement.spacedBy(Dimens.ItemSpacing)) {
        SectionHeader(title = "Vital Signs", icon = R.drawable.ic_ecg_heart)
        Text(
            "Tap any reading to learn what it means.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

        vitals.chunked(2).forEach { row ->
            Row(
                modifier = Modifier.height(IntrinsicSize.Min),
                horizontalArrangement = Arrangement.spacedBy(Dimens.ItemSpacing),
            ) {
                row.forEach { vital ->
                    VitalTile(
                        vital = vital,
                        onClick = { onSelect(vital) },
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                    )
                }
            }
        }
    }
}
