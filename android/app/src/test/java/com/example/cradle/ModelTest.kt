package com.example.cradle

import com.example.cradle.data.SampleData
import com.example.cradle.model.UpdateDraft
import com.example.cradle.model.UpdateFilter
import com.example.cradle.model.UpdateKind
import com.example.cradle.model.UpdateTemplate
import com.example.cradle.model.Vital
import com.example.cradle.model.VitalKind
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import java.time.Instant
import java.time.ZoneOffset

class ModelTest {
    @Test
    fun typicalRanges() {
        assertTrue(Vital(VitalKind.HeartRate, 140.0).isInTypicalRange)
        assertFalse(Vital(VitalKind.HeartRate, 190.0).isInTypicalRange)
        assertFalse(Vital(VitalKind.Oxygen, 88.0).isInTypicalRange)
        assertTrue(Vital(VitalKind.Temperature, 36.8).isInTypicalRange)
    }

    @Test
    fun dayOfBirthIsDayOne() {
        val birth = Instant.parse("2026-11-14T23:00:00Z")
        val baby = SampleData.babies().first().copy(birthDate = birth)

        assertEquals(1, baby.dayOfLife(Instant.parse("2026-11-14T23:30:00Z"), ZoneOffset.UTC))
        assertEquals(6, baby.dayOfLife(Instant.parse("2026-11-19T01:00:00Z"), ZoneOffset.UTC))
    }

    @Test
    fun allFilterIncludesEveryKind() {
        assertTrue(UpdateKind.entries.all(UpdateFilter.All::includes))
    }

    @Test
    fun everyKindBelongsToAFilterBesidesAll() {
        val specific = UpdateFilter.entries - UpdateFilter.All
        UpdateKind.entries.forEach { kind ->
            assertTrue("$kind has no filter", specific.any { it.includes(kind) })
        }
    }

    @Test
    fun draftNeedsTitleAndAuthor() {
        assertFalse(UpdateDraft().isReadyToSend)
        assertFalse(UpdateDraft(title = "Feed", author = "  ").isReadyToSend)
        assertFalse(UpdateDraft(title = "   ", author = "Nurse Siti").isReadyToSend)
        assertTrue(UpdateDraft(title = "Feed", author = "Nurse Siti").isReadyToSend)
    }

    @Test
    fun templateFillsDraftButKeepsAuthor() {
        val draft = UpdateDraft(author = "Nurse Siti").applying(UpdateTemplate.FullFeed)
        assertEquals(UpdateKind.Feed, draft.kind)
        assertEquals(UpdateTemplate.FullFeed.title, draft.title)
        assertEquals("Nurse Siti", draft.author)
    }

    @Test
    fun resetKeepsOnlyAuthor() {
        val draft = UpdateDraft(author = "Nurse Siti", changesStatus = true).applying(UpdateTemplate.Nappy)
        assertEquals(UpdateDraft(author = "Nurse Siti"), draft.reset())
    }

    @Test
    fun vitalFormatting() {
        assertEquals("36.8", Vital(VitalKind.Temperature, 36.8).formattedValue)
        assertEquals("2.36", Vital(VitalKind.Weight, 2.36).formattedValue)
        assertEquals("138", Vital(VitalKind.HeartRate, 138.0).formattedValue)
    }
}
