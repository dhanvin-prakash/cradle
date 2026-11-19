package com.example.cradle.ui.status

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.cradle.data.NurseryState
import com.example.cradle.model.Vital
import com.example.cradle.ui.AccountState
import com.example.cradle.ui.BabySwitcherState
import com.example.cradle.ui.components.AccountMenu
import com.example.cradle.ui.components.BabySwitcher
import com.example.cradle.ui.components.CradleScreenScaffold
import com.example.cradle.ui.components.EmptyState
import com.example.cradle.ui.components.RefreshButton
import com.example.cradle.ui.components.RefreshableList
import com.example.cradle.R

@Composable
fun StatusScreen(
    state: NurseryState,
    babySwitcher: BabySwitcherState,
    account: AccountState,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var selectedVital by remember { mutableStateOf<Vital?>(null) }
    val baby = state.selectedBaby

    CradleScreenScaffold(
        title = "Today",
        subtitle = baby?.fullName,
        modifier = modifier,
        actions = {
            BabySwitcher(babySwitcher)
            RefreshButton(isRefreshing = state.isRefreshing, onRefresh = onRefresh)
            AccountMenu(account)
        },
    ) { innerPadding ->
        if (baby == null) {
            EmptyState(
                icon = R.drawable.ic_child_care,
                title = "No Baby Linked",
                message = "Ask a nurse for your baby's link code to see their updates here.",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
            )
        } else {
            RefreshableList(
                isRefreshing = state.isRefreshing,
                onRefresh = onRefresh,
                innerPadding = innerPadding,
            ) {
                item { BabyHeaderCard(baby) }
                item { StatusCard(status = baby.status, lastUpdated = baby.lastUpdated) }
                item { VitalsSection(vitals = baby.vitals, onSelect = { selectedVital = it }) }
                item { FeedingCard(baby.feeding) }
                item { CareTeamCard(baby) }
            }
        }
    }

    selectedVital?.let { vital ->
        VitalDetailSheet(vital = vital, onDismiss = { selectedVital = null })
    }
}
