package com.example.cradle.ui.status

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.cradle.R
import com.example.cradle.model.Vital

/** Uses both an icon and words so the meaning never relies on colour alone. */
@Composable
fun VitalRangeLabel(vital: Vital) {
    val inRange = vital.isInTypicalRange
    val color = if (inRange) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primary

    Row(
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(if (inRange) R.drawable.ic_check_circle else R.drawable.ic_visibility),
            contentDescription = null,
            tint = color,
            modifier = Modifier.size(18.dp),
        )
        Text(
            if (inRange) "Typical range" else "Team is watching",
            style = MaterialTheme.typography.labelLarge,
            color = color,
        )
    }
}
