package com.example.cradle.model

/** One-tap starting points for the updates staff send most often. */
enum class UpdateTemplate(
    val shortTitle: String,
    val kind: UpdateKind,
    val title: String,
    val detail: String,
) {
    FullFeed("Feed", UpdateKind.Feed, "Finished a full feed", "Took the whole feed well and settled afterwards."),
    Nappy("Nappy", UpdateKind.Nappy, "Nappy change", "Wet nappy – all normal."),
    Sleeping("Sleeping", UpdateKind.NurseNote, "Sleeping peacefully", "Comfortable and resting well between cares."),
    SkinToSkin("Cuddle", UpdateKind.Cuddle, "Skin-to-skin cuddle", "Lovely cuddle time. Heart rate stayed nice and steady."),
    Weighed("Weight", UpdateKind.Weight, "Weighed today", "Weight is on track for this stage."),
    WardRound("Ward Round", UpdateKind.DoctorRound, "Ward round", "The doctor reviewed your baby today and is happy with the plan."),
}
