package com.example.cradle.ui

import com.example.cradle.model.Baby

data class BabySwitcherState(
    val babies: List<Baby>,
    val selectedBabyId: String?,
    val onSelect: (String) -> Unit,
)
