package com.example.cradle.ui.updates

import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.cradle.R
import com.example.cradle.data.NurseryState
import com.example.cradle.data.NurseryStore
import com.example.cradle.model.UpdateFilter
import com.example.cradle.ui.AccountState
import com.example.cradle.ui.BabySwitcherState
import com.example.cradle.ui.components.AccountMenu
import com.example.cradle.ui.components.BabySwitcher
import com.example.cradle.ui.components.CradleScreenScaffold
import com.example.cradle.ui.components.EmptyState
import com.example.cradle.ui.components.RefreshableList

@Composable
fun UpdatesScreen(
    state: NurseryState,
    babySwitcher: BabySwitcherState,
    account: AccountState,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var filter by rememberSaveable { mutableStateOf(UpdateFilter.All) }
    val baby = state.selectedBaby
    val days = remember(baby, filter) {
        baby?.let { NurseryStore.updateDays(it, filter) }.orEmpty()
    }

    CradleScreenScaffold(
        title = baby?.let { "${it.givenName}'s Updates" } ?: "Updates",
        modifier = modifier,
        actions = {
            BabySwitcher(babySwitcher)
            AccountMenu(account)
        },
    ) { innerPadding ->
        RefreshableList(
            isRefreshing = state.isRefreshing,
            onRefresh = onRefresh,
            innerPadding = innerPadding,
        ) {
            item { UpdateFilterBar(selection = filter, onSelect = { filter = it }) }

            if (days.isEmpty()) {
                item {
                    EmptyState(
                        icon = R.drawable.ic_inbox,
                        title = "No Updates Yet",
                        message = "New notes from the care team will appear here.",
                    )
                }
            }

            days.forEach { day ->
                item(key = day.day.toString()) { DayHeader(day.day) }
                items(day.updates, key = { it.id }) { update ->
                    UpdateRow(update = update, modifier = Modifier.animateItem())
                }
            }
        }
    }
}
