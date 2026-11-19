package com.example.cradle.ui.status

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.cradle.model.Vital

@Composable
fun VitalValue(
    vital: Vital,
    style: TextStyle = MaterialTheme.typography.headlineLargeEmphasized,
) {
    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(vital.formattedValue, style = style, modifier = Modifier.alignByBaseline())
        Text(
            vital.kind.unit,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.alignByBaseline(),
        )
    }
}
