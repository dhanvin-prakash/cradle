package com.example.cradle.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.example.cradle.R
import com.example.cradle.ui.AccountState
import com.example.cradle.ui.AppRole

/** MVP account switcher: toggles between the parent view and the care team (admin) view. */
@Composable
fun AccountMenu(account: AccountState) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        PinkTonalIconButton(onClick = { expanded = true }) {
            Icon(
                painter = painterResource(account.role.icon),
                contentDescription = "Account: ${account.role.title}",
            )
        }

        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            Text(
                "View as",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            )
            AppRole.entries.forEach { role ->
                val isSelected = role == account.role
                DropdownMenuItem(
                    text = { Text(role.title) },
                    onClick = {
                        account.onRoleChange(role)
                        expanded = false
                    },
                    leadingIcon = { Icon(painter = painterResource(role.icon), contentDescription = null) },
                    trailingIcon = {
                        if (isSelected) {
                            Icon(painter = painterResource(R.drawable.ic_check), contentDescription = null)
                        }
                    },
                    modifier = Modifier.semantics {
                        this.role = Role.RadioButton
                        selected = isSelected
                    },
                )
            }
        }
    }
}
