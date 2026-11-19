package com.example.cradle.data

import com.example.cradle.model.Baby

data class NurseryState(
    val babies: List<Baby> = emptyList(),
    val selectedBabyId: String? = null,
    val isRefreshing: Boolean = false,
    val unreadUpdateCount: Int = 0,
) {
    val selectedBaby: Baby?
        get() = babies.firstOrNull { it.id == selectedBabyId } ?: babies.firstOrNull()
}
