package com.example.cradle.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import com.example.cradle.R
import com.example.cradle.ui.BabySwitcherState

/** Lets parents of twins (or more) switch between babies. Hidden for a single baby. */
@Composable
fun BabySwitcher(state: BabySwitcherState) {
    if (state.babies.size < 2) return

    var expanded by remember { mutableStateOf(false) }

    Box {
        FilledTonalIconButton(onClick = { expanded = true }) {
            Icon(painter = painterResource(R.drawable.ic_group), contentDescription = "Switch baby")
        }

        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            state.babies.forEach { baby ->
                DropdownMenuItem(
                    text = { Text("${baby.fullName} · ${baby.ward.shortName}") },
                    onClick = {
                        state.onSelect(baby.id)
                        expanded = false
                    },
                    leadingIcon = {
                        Icon(painter = painterResource(R.drawable.ic_child_care), contentDescription = null)
                    },
                    trailingIcon = {
                        if (baby.id == state.selectedBabyId) {
                            Icon(painter = painterResource(R.drawable.ic_check), contentDescription = "Selected")
                        }
                    },
                )
            }
        }
    }
}
