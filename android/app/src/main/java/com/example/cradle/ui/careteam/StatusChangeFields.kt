package com.example.cradle.ui.careteam

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.example.cradle.R
import com.example.cradle.model.CareStatus
import com.example.cradle.model.UpdateDraft
import com.example.cradle.ui.theme.Dimens

@Composable
fun StatusChangeFields(
    draft: UpdateDraft,
    onChange: (UpdateDraft) -> Unit,
) {
    Column(verticalArrangement = Arrangement.spacedBy(Dimens.ItemSpacing)) {
        // The whole row is the toggle target, not just the small switch.
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 56.dp)
                .toggleable(
                    value = draft.changesStatus,
                    role = Role.Switch,
                    onValueChange = { onChange(draft.copy(changesStatus = it)) },
                ),
            horizontalArrangement = Arrangement.spacedBy(Dimens.ItemSpacing),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text("Also update status", style = MaterialTheme.typography.titleMedium)
                Text(
                    "The headline parents see first on the Today tab.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
            Switch(
                checked = draft.changesStatus,
                onCheckedChange = null,
                thumbContent = if (draft.changesStatus) {
                    {
                        Icon(
                            painter = painterResource(R.drawable.ic_check),
                            contentDescription = null,
                            modifier = Modifier.heightIn(max = SwitchDefaults.IconSize),
                        )
                    }
                } else {
                    null
                },
            )
        }

        AnimatedVisibility(visible = draft.changesStatus) {
            DropdownField(
                label = "New status",
                selected = draft.status,
                options = CareStatus.entries,
                optionLabel = { it.title },
                optionIcon = { it.icon },
                onSelect = { onChange(draft.copy(status = it)) },
            )
        }
    }
}
