package com.example.cradle

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.content.edit
import com.example.cradle.ui.AppRole
import com.example.cradle.ui.AppTab
import com.example.cradle.ui.CradleApp
import com.example.cradle.ui.theme.CradleTheme

private const val PREFS = "cradle"
private const val ROLE_KEY = "appRole"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        val prefs = getSharedPreferences(PREFS, Context.MODE_PRIVATE)

        // `--es initialTab updates --es role careteam` lets CI screenshot each tab and role.
        val initialTab = AppTab.fromName(intent.getStringExtra("initialTab"))
        val initialRole = AppRole.fromName(intent.getStringExtra("role") ?: prefs.getString(ROLE_KEY, null))

        setContent {
            CradleTheme {
                CradleApp(
                    initialTab = initialTab,
                    initialRole = initialRole,
                    onRoleChange = { role -> prefs.edit { putString(ROLE_KEY, role.name) } },
                )
            }
        }
    }
}
