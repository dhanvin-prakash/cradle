package com.example.cradle.model

import androidx.annotation.DrawableRes
import com.example.cradle.R

enum class UpdateKind(
    val title: String,
    @param:DrawableRes val icon: Int,
    /** Pink for baby-moment updates, blue for clinical ones. */
    val isMoment: Boolean,
) {
    Feed("Feed", R.drawable.ic_water_drop, isMoment = true),
    Nappy("Nappy", R.drawable.ic_baby_changing_station, isMoment = true),
    Weight("Weight", R.drawable.ic_monitor_weight, isMoment = false),
    Cuddle("Cuddle", R.drawable.ic_volunteer_activism, isMoment = true),
    NurseNote("Nurse note", R.drawable.ic_chat, isMoment = false),
    DoctorRound("Doctor's round", R.drawable.ic_stethoscope, isMoment = false),
}
