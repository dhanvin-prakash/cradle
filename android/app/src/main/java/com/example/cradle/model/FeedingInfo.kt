package com.example.cradle.model

import java.time.Instant

data class FeedingInfo(
    val lastFeed: Instant,
    val nextFeed: Instant,
    val method: String,
    val amountInMillilitres: Int,
)
