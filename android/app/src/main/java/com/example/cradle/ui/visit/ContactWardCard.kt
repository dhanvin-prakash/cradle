package com.example.cradle.ui.visit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.cradle.R
import com.example.cradle.model.Ward
import com.example.cradle.ui.components.SectionHeader
import com.example.cradle.ui.theme.Dimens

private const val DIRECTIONS = "geo:0,0?q=KK+Women's+and+Children's+Hospital,+100+Bukit+Timah+Road,+Singapore"

@Composable
fun ContactWardCard(ward: Ward) {
    val uriHandler = LocalUriHandler.current

    Column(verticalArrangement = Arrangement.spacedBy(Dimens.ItemSpacing)) {
        SectionHeader(title = "Get in Touch", icon = R.drawable.ic_call)
        Text(
            "Nurses are happy to talk any time, day or night. You're never a bother.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

        // Expressive buttons morph their corners when pressed.
        Button(
            onClick = { runCatching { uriHandler.openUri(ward.phoneNumber) } },
            shapes = ButtonDefaults.shapes(),
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = ButtonDefaults.MediumContainerHeight),
        ) {
            Icon(painter = painterResource(R.drawable.ic_call), contentDescription = null, modifier = Modifier.size(24.dp))
            Spacer(Modifier.width(8.dp))
            Text("Call ${ward.shortName}", style = MaterialTheme.typography.titleMedium)
        }

        FilledTonalButton(
            onClick = { runCatching { uriHandler.openUri(DIRECTIONS) } },
            shapes = ButtonDefaults.shapes(),
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = ButtonDefaults.MediumContainerHeight),
        ) {
            Icon(painter = painterResource(R.drawable.ic_map), contentDescription = null, modifier = Modifier.size(24.dp))
            Spacer(Modifier.width(8.dp))
            Text("Directions to Hospital", style = MaterialTheme.typography.titleMedium)
        }
    }
}
