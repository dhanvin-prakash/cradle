package com.example.cradle.model

import androidx.annotation.DrawableRes
import com.example.cradle.R

/** Parent-friendly summary of how a baby is doing right now. */
enum class CareStatus(
    val title: String,
    val message: String,
    @param:DrawableRes val icon: Int,
    val isCalm: Boolean,
) {
    Stable(
        title = "Stable and comfortable",
        message = "All checks look good. The nurses will keep caring for your baby around the clock.",
        icon = R.drawable.ic_heart_check,
        isCalm = true,
    ),
    Sleeping(
        title = "Sleeping peacefully",
        message = "Your baby is resting. Sleep helps little ones grow, so the team will let them rest.",
        icon = R.drawable.ic_bedtime,
        isCalm = true,
    ),
    Feeding(
        title = "Having a feed",
        message = "Your baby is feeding right now. You'll see how it went in Updates soon.",
        icon = R.drawable.ic_water_drop,
        isCalm = true,
    ),
    CloselyMonitored(
        title = "Being closely watched",
        message = "The care team is keeping an extra eye on your baby. This is common in the NICU, and they'll call you if anything changes.",
        icon = R.drawable.ic_visibility,
        isCalm = false,
    ),
    ReadyForHome(
        title = "Getting ready for home",
        message = "Your baby is doing so well that the team is planning for discharge. Ask the nurse about going-home lessons.",
        icon = R.drawable.ic_home,
        isCalm = true,
    ),
}
