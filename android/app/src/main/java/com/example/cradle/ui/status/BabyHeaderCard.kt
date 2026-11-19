package com.example.cradle.ui.status

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialShapes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.toShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.example.cradle.R
import com.example.cradle.model.Baby
import com.example.cradle.ui.components.dayMonth
import com.example.cradle.ui.theme.Dimens

@Composable
fun BabyHeaderCard(baby: Baby) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .semantics(mergeDescendants = true) {},
        shape = MaterialTheme.shapes.extraLarge,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        ),
    ) {
        Row(
            modifier = Modifier.padding(Dimens.CardPadding),
            horizontalArrangement = Arrangement.spacedBy(Dimens.ItemSpacing + 4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(Dimens.Avatar)
                    .background(MaterialTheme.colorScheme.primary, MaterialShapes.Cookie9Sided.toShape()),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = baby.initials,
                    style = MaterialTheme.typography.titleLargeEmphasized,
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            }

            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(baby.fullName, style = MaterialTheme.typography.headlineSmallEmphasized)
                Text(
                    "Day ${baby.dayOfLife()} · Born ${baby.birthDate.dayMonth()}",
                    style = MaterialTheme.typography.bodyLarge,
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_bed),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp),
                    )
                    Text(
                        "${baby.ward.shortName} · Bed ${baby.bed}",
                        style = MaterialTheme.typography.bodyLargeEmphasized,
                    )
                }
            }
        }
    }
}
