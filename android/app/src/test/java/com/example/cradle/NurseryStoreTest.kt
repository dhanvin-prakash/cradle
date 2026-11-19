package com.example.cradle

import com.example.cradle.data.NurseryStore
import com.example.cradle.data.SampleData
import com.example.cradle.model.CareStatus
import com.example.cradle.model.UpdateDraft
import com.example.cradle.model.UpdateFilter
import com.example.cradle.model.UpdateKind
import com.example.cradle.model.UpdateTemplate
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import java.time.Instant
import java.time.ZoneOffset
import kotlin.time.Duration

class NurseryStoreTest {
    private fun readyDraft() = UpdateDraft(author = "Nurse Siti").applying(UpdateTemplate.FullFeed)

    @Test
    fun selectsFirstBabyByDefault() {
        val babies = SampleData.babies()
        val store = NurseryStore(babies)
        assertEquals(babies.first().id, store.state.value.selectedBaby?.id)
    }

    @Test
    fun switchingBabyUpdatesSelection() {
        val babies = SampleData.babies()
        val store = NurseryStore(babies)
        store.select(babies[1].id)
        assertEquals(babies[1].id, store.state.value.selectedBaby?.id)
    }

    @Test
    fun emptyStoreHasNoSelection() {
        assertNull(NurseryStore(emptyList()).state.value.selectedBaby)
    }

    @Test
    fun feedFilterOnlyReturnsFeeds() {
        val baby = SampleData.babies().first()
        val updates = NurseryStore.updateDays(baby, UpdateFilter.Feeds, ZoneOffset.UTC).flatMap { it.updates }
        assertTrue(updates.isNotEmpty())
        assertTrue(updates.all { it.kind == UpdateKind.Feed })
    }

    @Test
    fun updatesAreGroupedNewestFirst() {
        val baby = SampleData.babies().first()
        val days = NurseryStore.updateDays(baby, UpdateFilter.All, ZoneOffset.UTC)

        assertEquals(days.map { it.day }.sortedDescending(), days.map { it.day })
        days.forEach { day ->
            assertEquals(day.updates.sortedByDescending { it.date }, day.updates)
        }
        assertEquals(baby.updates.size, days.sumOf { it.updates.size })
    }

    @Test
    fun refreshStampsLatestTime() = runTest {
        val store = NurseryStore(refreshDelay = Duration.ZERO)
        val now = Instant.parse("2026-11-19T10:00:00Z")

        store.refresh { now }

        assertTrue(store.state.value.babies.all { it.lastUpdated == now })
        assertFalse(store.state.value.isRefreshing)
    }

    @Test
    fun sendingAddsUpdateForParents() {
        val store = NurseryStore()
        val baby = store.state.value.babies.last()
        val now = Instant.now().plusSeconds(60)

        assertTrue(store.send(readyDraft(), baby.id, now))

        val updated = store.state.value.babies.last()
        assertEquals(baby.updates.size + 1, updated.updates.size)
        assertEquals(UpdateTemplate.FullFeed.title, updated.updates.maxBy { it.date }.title)
        assertEquals(now, updated.lastUpdated)
        assertEquals(baby.status, updated.status)
        assertEquals(1, store.state.value.unreadUpdateCount)
    }

    @Test
    fun sendingCanChangeStatus() {
        val store = NurseryStore()
        val baby = store.state.value.babies.last()

        store.send(readyDraft().copy(changesStatus = true, status = CareStatus.ReadyForHome), baby.id)

        assertEquals(CareStatus.ReadyForHome, store.state.value.babies.last().status)
    }

    @Test
    fun incompleteDraftIsNotSent() {
        val store = NurseryStore()
        val baby = store.state.value.babies.first()

        assertFalse(store.send(UpdateDraft(), baby.id))
        assertEquals(baby.updates.size, store.state.value.babies.first().updates.size)
        assertEquals(0, store.state.value.unreadUpdateCount)
    }

    @Test
    fun unknownBabyIsNotSent() {
        assertFalse(NurseryStore().send(readyDraft(), "nobody"))
    }

    @Test
    fun readingUpdatesClearsBadge() {
        val store = NurseryStore()
        store.send(readyDraft(), store.state.value.babies.first().id)
        store.markUpdatesRead()
        assertEquals(0, store.state.value.unreadUpdateCount)
    }
}
