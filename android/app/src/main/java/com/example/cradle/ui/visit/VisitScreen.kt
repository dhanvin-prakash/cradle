package com.example.cradle.ui.visit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.example.cradle.model.Baby
import com.example.cradle.ui.BabySwitcherState
import com.example.cradle.ui.components.BabySwitcher
import com.example.cradle.ui.components.CradleScreenScaffold
import com.example.cradle.ui.theme.Dimens

@Composable
fun VisitScreen(
    baby: Baby?,
    babySwitcher: BabySwitcherState,
    modifier: Modifier = Modifier,
) {
    CradleScreenScaffold(
        title = "Visit",
        subtitle = baby?.ward?.displayName,
        modifier = modifier,
        actions = { BabySwitcher(babySwitcher) },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(Dimens.ScreenPadding),
            verticalArrangement = Arrangement.spacedBy(Dimens.SectionSpacing),
        ) {
            if (baby != null) {
                item { VisitingHoursCard(baby.ward) }
                item { ContactWardCard(baby.ward) }
            }
            item { VisitChecklistCard() }
            item {
                Text(
                    "Sample information for demonstration. Please confirm visiting rules with the ward.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}
