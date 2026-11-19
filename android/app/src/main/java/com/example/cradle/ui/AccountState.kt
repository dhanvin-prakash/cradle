package com.example.cradle.ui

data class AccountState(
    val role: AppRole,
    val onRoleChange: (AppRole) -> Unit,
)
