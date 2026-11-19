package com.example.cradle.model

import androidx.annotation.DrawableRes
import com.example.cradle.R

enum class UpdateFilter(
    val title: String,
    @param:DrawableRes val icon: Int,
) {
    All("All", R.drawable.ic_grid_view),
    Feeds("Feeds", R.drawable.ic_water_drop),
    Care("Care", R.drawable.ic_volunteer_activism),
    Notes("Notes", R.drawable.ic_chat);

    fun includes(kind: UpdateKind): Boolean = when (this) {
        All -> true
        Feeds -> kind == UpdateKind.Feed
        Care -> kind in setOf(UpdateKind.Nappy, UpdateKind.Weight, UpdateKind.Cuddle)
        Notes -> kind in setOf(UpdateKind.NurseNote, UpdateKind.DoctorRound)
    }
}
