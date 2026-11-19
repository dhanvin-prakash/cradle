package com.example.cradle.ui.visit

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
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.example.cradle.R
import com.example.cradle.ui.components.InfoCard
import com.example.cradle.ui.theme.Dimens

private val checklist = listOf(
    R.drawable.ic_clean_hands to "Wash or sanitise your hands at the door",
    R.drawable.ic_masks to "Stay home if you have a cough, fever or cold",
    R.drawable.ic_notifications_off to "Switch your phone to silent near the cots",
    R.drawable.ic_badge to "Bring your parent pass for the ward",
    R.drawable.ic_favorite to "Ask about skin-to-skin cuddles",
)

@Composable
fun VisitChecklistCard() {
    InfoCard(
        title = "Before You Visit",
        icon = R.drawable.ic_checklist,
        containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.7f),
    ) {
        checklist.forEach { (icon, text) ->
            Row(
                modifier = Modifier.semantics(mergeDescendants = true) {},
                horizontalArrangement = Arrangement.spacedBy(Dimens.ItemSpacing),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(icon),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(24.dp),
                )
                Text(text, style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}
