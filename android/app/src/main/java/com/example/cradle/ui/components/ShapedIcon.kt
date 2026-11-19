package com.example.cradle.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.toShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.graphics.shapes.RoundedPolygon
import com.example.cradle.ui.theme.Dimens

/** An icon sitting inside one of the Material 3 Expressive shapes (cookie, clover, sunny…). */
@Composable
fun ShapedIcon(
    @DrawableRes icon: Int,
    shape: RoundedPolygon,
    containerColor: Color,
    contentColor: Color,
    modifier: Modifier = Modifier,
    size: Dp = Dimens.IconContainer,
    contentDescription: String? = null,
) {
    Box(
        modifier = modifier
            .size(size)
            .background(containerColor, shape.toShape()),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = contentDescription,
            tint = contentColor,
            modifier = Modifier.size(size / 2),
        )
    }
}
