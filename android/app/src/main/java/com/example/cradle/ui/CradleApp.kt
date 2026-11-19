package com.example.cradle.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cradle.data.NurseryViewModel
import com.example.cradle.ui.careteam.CareTeamScreen
import com.example.cradle.ui.components.NurseryBackground
import com.example.cradle.ui.status.StatusScreen
import com.example.cradle.ui.updates.UpdatesScreen
import com.example.cradle.ui.visit.VisitScreen

@Composable
fun CradleApp(
    initialTab: AppTab,
    initialRole: AppRole,
    onRoleChange: (AppRole) -> Unit,
    viewModel: NurseryViewModel = viewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    var role by rememberSaveable { mutableStateOf(initialRole) }
    var selectedTab by rememberSaveable {
        mutableStateOf(if (initialTab in initialRole.tabs) initialTab else initialRole.defaultTab)
    }

    val account = AccountState(role = role) { newRole ->
        role = newRole
        if (selectedTab !in newRole.tabs) selectedTab = newRole.defaultTab
        onRoleChange(newRole)
    }

    // Only parents get the unread badge; staff wrote the updates themselves.
    val badgeCount = if (role == AppRole.Parent) state.unreadUpdateCount else 0

    LaunchedEffect(selectedTab, state.unreadUpdateCount) {
        if (selectedTab == AppTab.Updates && state.unreadUpdateCount > 0) {
            viewModel.markUpdatesRead()
        }
    }

    NurseryBackground {
        Scaffold(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onBackground,
            bottomBar = {
                CradleNavigationBar(
                    tabs = role.tabs,
                    selectedTab = selectedTab,
                    unreadUpdateCount = badgeCount,
                    onSelect = { selectedTab = it },
                )
            },
        ) { padding ->
            val modifier = Modifier.padding(bottom = padding.calculateBottomPadding())
            val babySwitcher = BabySwitcherState(
                babies = state.babies,
                selectedBabyId = state.selectedBaby?.id,
                onSelect = viewModel::select,
            )

            when (selectedTab) {
                AppTab.Status -> StatusScreen(
                    state = state,
                    babySwitcher = babySwitcher,
                    account = account,
                    onRefresh = viewModel::refresh,
                    modifier = modifier,
                )
                AppTab.Updates -> UpdatesScreen(
                    state = state,
                    babySwitcher = babySwitcher,
                    account = account,
                    onRefresh = viewModel::refresh,
                    modifier = modifier,
                )
                AppTab.Visit -> VisitScreen(
                    baby = state.selectedBaby,
                    babySwitcher = babySwitcher,
                    account = account,
                    modifier = modifier,
                )
                AppTab.CareTeam -> CareTeamScreen(
                    babies = state.babies,
                    defaultRecipientId = state.selectedBaby?.id,
                    account = account,
                    onSend = viewModel::send,
                    modifier = modifier,
                )
            }
        }
    }
}
