package com.example.cradle.ui.careteam

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import com.example.cradle.R
import com.example.cradle.model.Baby
import com.example.cradle.ui.components.SectionHeader
import com.example.cradle.ui.theme.Dimens

@Composable
fun RecipientPicker(
    babies: List<Baby>,
    selectedId: String?,
    onSelect: (String) -> Unit,
) {
    Column(verticalArrangement = Arrangement.spacedBy(Dimens.ItemSpacing)) {
        SectionHeader(title = "Baby", icon = R.drawable.ic_child_care)

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(ButtonGroupDefaults.ConnectedSpaceBetween),
        ) {
            babies.forEachIndexed { index, baby ->
                val checked = baby.id == selectedId

                ToggleButton(
                    checked = checked,
                    onCheckedChange = { onSelect(baby.id) },
                    modifier = Modifier
                        .weight(1f)
                        .semantics { role = Role.RadioButton },
                    shapes = when {
                        babies.size == 1 -> ToggleButtonDefaults.shapesFor(ToggleButtonDefaults.size)
                        index == 0 -> ButtonGroupDefaults.connectedLeadingButtonShapes()
                        index == babies.lastIndex -> ButtonGroupDefaults.connectedTrailingButtonShapes()
                        else -> ButtonGroupDefaults.connectedMiddleButtonShapes()
                    },
                    icon = if (checked) {
                        {
                            Icon(
                                painter = painterResource(R.drawable.ic_check),
                                contentDescription = null,
                                modifier = Modifier.size(ToggleButtonDefaults.IconSize),
                            )
                        }
                    } else {
                        null
                    },
                ) {
                    Text("${baby.givenName} · ${baby.bed}", maxLines = 1, overflow = TextOverflow.Ellipsis)
                }
            }
        }
    }
}
