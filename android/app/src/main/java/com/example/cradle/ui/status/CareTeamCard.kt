package com.example.cradle.ui.status

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.example.cradle.R
import com.example.cradle.model.Baby
import com.example.cradle.ui.components.InfoCard
import com.example.cradle.ui.components.LabeledRow
import com.example.cradle.ui.theme.Dimens

@Composable
fun CareTeamCard(baby: Baby) {
    InfoCard(
        title = "Care Team",
        icon = R.drawable.ic_group,
        containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.7f),
    ) {
        LabeledRow(R.drawable.ic_medical_services, "Nurse today", baby.nurse)
        LabeledRow(R.drawable.ic_stethoscope, "Doctor", "Dr ${baby.doctor}")
        HorizontalDivider()
        Row(horizontalArrangement = Arrangement.spacedBy(Dimens.ItemSpacing)) {
            Icon(
                painter = painterResource(R.drawable.ic_format_quote),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(24.dp),
            )
            Text(
                baby.nurseNote,
                style = MaterialTheme.typography.bodyLarge,
                fontStyle = FontStyle.Italic,
            )
        }
    }
}
