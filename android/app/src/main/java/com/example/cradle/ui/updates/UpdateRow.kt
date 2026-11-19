package com.example.cradle.ui.updates

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialShapes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.example.cradle.model.CareUpdate
import com.example.cradle.model.UpdateKind
import com.example.cradle.ui.components.ShapedIcon
import com.example.cradle.ui.components.shortTime
import com.example.cradle.ui.theme.Dimens

/** Each kind of update gets its own expressive shape, so the timeline is scannable at a glance. */
private fun UpdateKind.shape() = when (this) {
    UpdateKind.Feed -> MaterialShapes.Cookie6Sided
    UpdateKind.Nappy -> MaterialShapes.Sunny
    UpdateKind.Weight -> MaterialShapes.Gem
    UpdateKind.Cuddle -> MaterialShapes.Heart
    UpdateKind.NurseNote -> MaterialShapes.Clover4Leaf
    UpdateKind.DoctorRound -> MaterialShapes.Puffy
}

@Composable
fun UpdateRow(
    update: CareUpdate,
    modifier: Modifier = Modifier,
) {
    val colors = MaterialTheme.colorScheme

    Card(
        modifier = modifier
            .fillMaxWidth()
            .semantics(mergeDescendants = true) {},
        shape = MaterialTheme.shapes.extraLarge,
        colors = CardDefaults.cardColors(
            containerColor = colors.surfaceContainerLowest.copy(alpha = 0.85f),
            contentColor = colors.onSurface,
        ),
    ) {
        Row(
            modifier = Modifier.padding(Dimens.CardPadding),
            horizontalArrangement = Arrangement.spacedBy(Dimens.ItemSpacing + 4.dp),
        ) {
            ShapedIcon(
                icon = update.kind.icon,
                shape = update.kind.shape(),
                containerColor = if (update.kind.isMoment) colors.primaryContainer else colors.secondaryContainer,
                contentColor = if (update.kind.isMoment) colors.onPrimaryContainer else colors.onSecondaryContainer,
            )

            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(update.title, style = MaterialTheme.typography.titleMediumEmphasized)
                if (update.detail.isNotBlank()) {
                    Text(update.detail, style = MaterialTheme.typography.bodyLarge)
                }
                Text(
                    "${update.author} · ${update.date.shortTime()}",
                    style = MaterialTheme.typography.labelLarge,
                    color = colors.onSurfaceVariant,
                )
            }
        }
    }
}
