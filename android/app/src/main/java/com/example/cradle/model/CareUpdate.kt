package com.example.cradle.model

import java.time.Instant
import java.util.UUID

data class CareUpdate(
    val id: String = UUID.randomUUID().toString(),
    val date: Instant,
    val kind: UpdateKind,
    val title: String,
    val detail: String,
    val author: String,
)
