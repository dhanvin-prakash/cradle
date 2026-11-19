package com.example.cradle.data

import com.example.cradle.model.Baby
import com.example.cradle.model.UpdateDay
import com.example.cradle.model.UpdateDraft
import com.example.cradle.model.UpdateFilter
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.Instant
import java.time.ZoneId
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

/** Holds the babies a parent can see and simulates syncing with the ward. */
class NurseryStore(
    babies: List<Baby> = SampleData.babies(),
    private val refreshDelay: Duration = 1.seconds,
) {
    private val _state = MutableStateFlow(
        NurseryState(babies = babies, selectedBabyId = babies.firstOrNull()?.id),
    )
    val state: StateFlow<NurseryState> = _state.asStateFlow()

    fun select(babyId: String) {
        _state.update { it.copy(selectedBabyId = babyId) }
    }

    /** Pretends to fetch the latest readings from the ward. */
    suspend fun refresh(now: () -> Instant = Instant::now) {
        if (_state.value.isRefreshing) return
        _state.update { it.copy(isRefreshing = true) }
        try {
            delay(refreshDelay)
            val stamp = now()
            _state.update { state ->
                state.copy(babies = state.babies.map { it.copy(lastUpdated = stamp) })
            }
        } finally {
            _state.update { it.copy(isRefreshing = false) }
        }
    }

    /** Publishes a staff update to a baby's parents, optionally changing the headline status. */
    fun send(draft: UpdateDraft, babyId: String, now: Instant = Instant.now()): Boolean {
        if (!draft.isReadyToSend) return false
        if (_state.value.babies.none { it.id == babyId }) return false

        _state.update { state ->
            state.copy(
                babies = state.babies.map { baby ->
                    if (baby.id != babyId) {
                        baby
                    } else {
                        baby.copy(
                            updates = baby.updates + draft.toUpdate(now),
                            lastUpdated = now,
                            status = if (draft.changesStatus) draft.status else baby.status,
                        )
                    }
                },
                unreadUpdateCount = state.unreadUpdateCount + 1,
            )
        }
        return true
    }

    fun markUpdatesRead() {
        _state.update { it.copy(unreadUpdateCount = 0) }
    }

    companion object {
        /** Updates matching [filter], grouped by calendar day with the newest day and update first. */
        fun updateDays(
            baby: Baby,
            filter: UpdateFilter,
            zone: ZoneId = ZoneId.systemDefault(),
        ): List<UpdateDay> =
            baby.updates
                .filter { filter.includes(it.kind) }
                .sortedByDescending { it.date }
                .groupBy { it.date.atZone(zone).toLocalDate() }
                .map { (day, updates) -> UpdateDay(day, updates) }
                .sortedByDescending { it.day }
    }
}
