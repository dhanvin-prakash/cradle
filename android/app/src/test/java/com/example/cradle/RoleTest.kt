package com.example.cradle

import com.example.cradle.ui.AppRole
import com.example.cradle.ui.AppTab
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class RoleTest {
    @Test
    fun parentsNeverSeeTheComposer() {
        assertFalse(AppTab.CareTeam in AppRole.Parent.tabs)
        assertEquals(AppTab.Status, AppRole.Parent.defaultTab)
    }

    @Test
    fun careTeamStartsOnComposerAndHasNoVisitTab() {
        assertEquals(AppTab.CareTeam, AppRole.CareTeam.defaultTab)
        assertFalse(AppTab.Visit in AppRole.CareTeam.tabs)
    }

    @Test
    fun bothRolesCanReadUpdates() {
        AppRole.entries.forEach { assertTrue(AppTab.Updates in it.tabs) }
    }

    @Test
    fun unknownRoleFallsBackToParent() {
        assertEquals(AppRole.Parent, AppRole.fromName(null))
        assertEquals(AppRole.Parent, AppRole.fromName("nonsense"))
        assertEquals(AppRole.CareTeam, AppRole.fromName("careteam"))
    }
}
