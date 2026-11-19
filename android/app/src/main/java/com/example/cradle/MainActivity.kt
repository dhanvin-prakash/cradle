package com.example.cradle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.cradle.ui.AppTab
import com.example.cradle.ui.CradleApp
import com.example.cradle.ui.theme.CradleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        // `adb shell am start ... --es initialTab updates` lets CI screenshot each tab.
        val initialTab = AppTab.fromName(intent.getStringExtra("initialTab"))

        setContent {
            CradleTheme {
                CradleApp(initialTab = initialTab)
            }
        }
    }
}
