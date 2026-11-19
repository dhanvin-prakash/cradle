package com.example.cradle.ui

import androidx.compose.foundation.layout.padding
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
    viewModel: NurseryViewModel = viewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    var selectedTab by rememberSaveable { mutableStateOf(initialTab) }

    LaunchedEffect(selectedTab, state.unreadUpdateCount) {
        if (selectedTab == AppTab.Updates && state.unreadUpdateCount > 0) {
            viewModel.markUpdatesRead()
        }
    }

    NurseryBackground {
        Scaffold(
            containerColor = Color.Transparent,
            bottomBar = {
                CradleNavigationBar(
                    selectedTab = selectedTab,
                    unreadUpdateCount = state.unreadUpdateCount,
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
                    onRefresh = viewModel::refresh,
                    modifier = modifier,
                )
                AppTab.Updates -> UpdatesScreen(
                    state = state,
                    babySwitcher = babySwitcher,
                    onRefresh = viewModel::refresh,
                    modifier = modifier,
                )
                AppTab.Visit -> VisitScreen(
                    baby = state.selectedBaby,
                    babySwitcher = babySwitcher,
                    modifier = modifier,
                )
                AppTab.CareTeam -> CareTeamScreen(
                    babies = state.babies,
                    defaultRecipientId = state.selectedBaby?.id,
                    onSend = viewModel::send,
                    modifier = modifier,
                )
            }
        }
    }
}
