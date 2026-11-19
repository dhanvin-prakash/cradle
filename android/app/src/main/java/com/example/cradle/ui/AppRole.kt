package com.example.cradle.ui

import androidx.annotation.DrawableRes
import com.example.cradle.R

/** Who is using the app. A simple toggle for the MVP; real sign-in comes later. */
enum class AppRole(
    val title: String,
    @param:DrawableRes val icon: Int,
    /** Parents see their baby; staff get the update composer first. */
    val tabs: List<AppTab>,
) {
    Parent(
        title = "Parent",
        icon = R.drawable.ic_account_circle,
        tabs = listOf(AppTab.Status, AppTab.Updates, AppTab.Visit),
    ),
    CareTeam(
        title = "Care Team (Admin)",
        icon = R.drawable.ic_admin_panel_settings,
        tabs = listOf(AppTab.CareTeam, AppTab.Status, AppTab.Updates),
    );

    val defaultTab: AppTab get() = tabs.first()

    companion object {
        fun fromName(name: String?): AppRole =
            entries.firstOrNull { it.name.equals(name, ignoreCase = true) } ?: Parent
    }
}
