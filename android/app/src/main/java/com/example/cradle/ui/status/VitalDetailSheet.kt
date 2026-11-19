package com.example.cradle.ui.status

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import com.example.cradle.R
import com.example.cradle.model.Vital
import com.example.cradle.ui.components.LabeledRow
import com.example.cradle.ui.components.SectionHeader
import com.example.cradle.ui.theme.Dimens

@Composable
fun VitalDetailSheet(
    vital: Vital,
    onDismiss: () -> Unit,
) {
    val kind = vital.kind

    ModalBottomSheet(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = Dimens.ScreenPadding)
                .padding(bottom = Dimens.SectionSpacing)
                .navigationBarsPadding(),
            verticalArrangement = Arrangement.spacedBy(Dimens.SectionSpacing),
        ) {
            Text(
                kind.title,
                style = MaterialTheme.typography.headlineMediumEmphasized,
                modifier = Modifier.semantics { heading() },
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.extraLarge,
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                ),
            ) {
                Column(
                    modifier = Modifier.padding(Dimens.CardPadding),
                    verticalArrangement = Arrangement.spacedBy(Dimens.ItemSpacing),
                ) {
                    VitalValue(vital, style = MaterialTheme.typography.displayMediumEmphasized)
                    VitalRangeLabel(vital)
                }
            }

            Column(verticalArrangement = Arrangement.spacedBy(Dimens.ItemSpacing)) {
                SectionHeader("What this means", R.drawable.ic_info)
                Text(kind.explanation, style = MaterialTheme.typography.bodyLarge)
            }

            LabeledRow(
                icon = R.drawable.ic_check_circle,
                label = "Typical for newborns",
                value = "${kind.format(kind.typicalRange.start)} – ${kind.format(kind.typicalRange.endInclusive)} ${kind.unit}",
            )

            LabeledRow(
                icon = R.drawable.ic_help,
                label = "Questions?",
                value = "Ask your baby's nurse",
            )
        }
    }
}
