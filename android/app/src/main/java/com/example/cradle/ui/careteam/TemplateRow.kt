package com.example.cradle.ui.careteam

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.example.cradle.R
import com.example.cradle.model.UpdateTemplate
import com.example.cradle.ui.components.SectionHeader
import com.example.cradle.ui.theme.Dimens

@Composable
fun TemplateRow(onApply: (UpdateTemplate) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(Dimens.ItemSpacing)) {
        SectionHeader(title = "Quick Start", icon = R.drawable.ic_send)

        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(UpdateTemplate.entries) { template ->
                AssistChip(
                    onClick = { onApply(template) },
                    label = { Text(template.shortTitle) },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(template.kind.icon),
                            contentDescription = null,
                            modifier = Modifier,
                        )
                    },
                    shape = MaterialTheme.shapes.large,
                    colors = AssistChipDefaults.assistChipColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerLowest.copy(alpha = 0.85f),
                        leadingIconContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    modifier = Modifier.semantics {
                        onClick(label = "Fill in “${template.title}”") { onApply(template); true }
                    },
                )
            }
        }

        Text(
            "Tap a template, then edit the words to make them personal.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}
