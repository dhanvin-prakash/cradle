package com.example.cradle.ui.status

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.example.cradle.R
import com.example.cradle.model.CareStatus
import com.example.cradle.ui.components.relativeToNow
import com.example.cradle.ui.theme.Dimens
import java.time.Instant

@Composable
fun StatusCard(
    status: CareStatus,
    lastUpdated: Instant,
) {
    val colors = MaterialTheme.colorScheme
    // Pink for everyday states; the calm blue is reserved for "being closely watched".
    val container = if (status.isCalm) colors.primaryContainer else colors.secondaryContainer
    val onContainer = if (status.isCalm) colors.onPrimaryContainer else colors.onSecondaryContainer
    val accent = if (status.isCalm) colors.primary else colors.secondary

    // A slowly turning "sunny" shape: a gentle sign the status is live.
    // Compose honours the system "remove animations" setting automatically.
    val rotation by rememberInfiniteTransition(label = "statusShape").animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(tween(20_000, easing = LinearEasing), RepeatMode.Restart),
        label = "rotation",
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .semantics(mergeDescendants = true) {},
        shape = MaterialTheme.shapes.extraLarge,
        colors = CardDefaults.cardColors(containerColor = container, contentColor = onContainer),
    ) {
        Column(
            modifier = Modifier.padding(Dimens.CardPadding),
            verticalArrangement = Arrangement.spacedBy(Dimens.ItemSpacing),
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(Dimens.ItemSpacing + 4.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(modifier = Modifier.size(56.dp), contentAlignment = Alignment.Center) {
                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .rotate(rotation)
                            .background(accent, MaterialShapes.Sunny.toShape()),
                    )
                    Icon(
                        painter = painterResource(status.icon),
                        contentDescription = null,
                        tint = colors.surface,
                        modifier = Modifier.size(28.dp),
                    )
                }
                Text(status.title, style = MaterialTheme.typography.titleLargeEmphasized)
            }

            Text(status.message, style = MaterialTheme.typography.bodyLarge)

            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_history),
                    contentDescription = null,
                    modifier = Modifier.size(18.dp),
                )
                Text("Updated ${lastUpdated.relativeToNow()}", style = MaterialTheme.typography.labelLarge)
            }
        }
    }
}
