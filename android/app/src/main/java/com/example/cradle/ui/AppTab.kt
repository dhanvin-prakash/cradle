package com.example.cradle.ui

import androidx.annotation.DrawableRes
import com.example.cradle.R

enum class AppTab(
    val title: String,
    @param:DrawableRes val icon: Int,
) {
    Status("Today", R.drawable.ic_monitor_heart),
    Updates("Updates", R.drawable.ic_clinical_notes),
    Visit("Visit", R.drawable.ic_family_restroom),
    CareTeam("Send Update", R.drawable.ic_edit_square);

    companion object {
        fun fromName(name: String?): AppTab =
            entries.firstOrNull { it.name.equals(name, ignoreCase = true) } ?: Status
    }
}
