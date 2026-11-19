package com.example.cradle.ui.updates

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonGroupDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.ToggleButton
import androidx.compose.material3.ToggleButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import com.example.cradle.model.UpdateFilter

/** Connected button group: the selected button morphs into a full pill. */
@Composable
fun UpdateFilterBar(
    selection: UpdateFilter,
    onSelect: (UpdateFilter) -> Unit,
) {
    val filters = UpdateFilter.entries

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(ButtonGroupDefaults.ConnectedSpaceBetween),
    ) {
        filters.forEachIndexed { index, filter ->
            val checked = filter == selection

            ToggleButton(
                checked = checked,
                onCheckedChange = { onSelect(filter) },
                modifier = Modifier
                    .weight(1f)
                    .semantics { role = Role.RadioButton },
                shapes = when (index) {
                    0 -> ButtonGroupDefaults.connectedLeadingButtonShapes()
                    filters.lastIndex -> ButtonGroupDefaults.connectedTrailingButtonShapes()
                    else -> ButtonGroupDefaults.connectedMiddleButtonShapes()
                },
                icon = if (checked) {
                    {
                        Icon(
                            painter = painterResource(filter.icon),
                            contentDescription = null,
                            modifier = Modifier.size(ToggleButtonDefaults.IconSize),
                        )
                    }
                } else {
                    null
                },
            ) {
                Text(filter.title, maxLines = 1, overflow = TextOverflow.Ellipsis)
            }
        }
    }
}
