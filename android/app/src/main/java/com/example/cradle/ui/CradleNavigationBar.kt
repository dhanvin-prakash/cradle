package com.example.cradle.ui

import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShortNavigationBar
import androidx.compose.material3.ShortNavigationBarItem
import androidx.compose.material3.ShortNavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import com.example.cradle.ui.theme.accentText

/** Material 3 Expressive short navigation bar with an unread badge on Updates. */
@Composable
fun CradleNavigationBar(
    selectedTab: AppTab,
    unreadUpdateCount: Int,
    onSelect: (AppTab) -> Unit,
) {
    ShortNavigationBar(containerColor = MaterialTheme.colorScheme.surfaceContainer) {
        AppTab.entries.forEach { tab ->
            val badgeCount = if (tab == AppTab.Updates) unreadUpdateCount else 0

            ShortNavigationBarItem(
                selected = tab == selectedTab,
                onClick = { onSelect(tab) },
                icon = {
                    BadgedBox(
                        badge = {
                            if (badgeCount > 0) {
                                Badge(
                                    modifier = Modifier.semantics {
                                        contentDescription = "$badgeCount new"
                                    },
                                ) {
                                    Text(badgeCount.toString())
                                }
                            }
                        },
                    ) {
                        Icon(painter = painterResource(tab.icon), contentDescription = null)
                    }
                },
                label = { Text(tab.title) },
                colors = ShortNavigationBarItemDefaults.colors(
                    selectedTextColorTopIconPosition = MaterialTheme.colorScheme.accentText,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                ),
            )
        }
    }
}
