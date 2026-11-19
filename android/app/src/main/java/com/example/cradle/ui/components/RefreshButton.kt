package com.example.cradle.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.LoadingIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.cradle.R

/** Shows the expressive shape-morphing loading indicator while syncing. */
@Composable
fun RefreshButton(
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
) {
    PinkTonalIconButton(onClick = onRefresh, enabled = !isRefreshing) {
        Box(contentAlignment = Alignment.Center) {
            if (isRefreshing) {
                LoadingIndicator(modifier = Modifier.size(28.dp))
            } else {
                Icon(painter = painterResource(R.drawable.ic_refresh), contentDescription = "Refresh")
            }
        }
    }
}
