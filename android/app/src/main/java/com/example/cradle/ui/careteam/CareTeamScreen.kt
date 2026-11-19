package com.example.cradle.ui.careteam

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.cradle.R
import com.example.cradle.model.Baby
import com.example.cradle.model.UpdateDraft
import com.example.cradle.ui.components.CradleScreenScaffold
import com.example.cradle.ui.theme.Dimens
import kotlinx.coroutines.launch

/** Lets nurses and doctors write an update that appears instantly in the parents' timeline. */
@Composable
fun CareTeamScreen(
    babies: List<Baby>,
    defaultRecipientId: String?,
    onSend: (UpdateDraft, String) -> Boolean,
    modifier: Modifier = Modifier,
) {
    var draft by remember { mutableStateOf(UpdateDraft()) }
    var recipientId by rememberSaveable { mutableStateOf(defaultRecipientId) }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val haptics = LocalHapticFeedback.current
    val focusManager = LocalFocusManager.current

    val recipient = babies.firstOrNull { it.id == recipientId }
    val canSend = draft.isReadyToSend && recipient != null

    fun send() {
        val baby = recipient ?: return
        if (onSend(draft, baby.id)) {
            draft = draft.reset()
            focusManager.clearFocus()
            haptics.performHapticFeedback(HapticFeedbackType.Confirm)
            scope.launch {
                snackbarHostState.showSnackbar("Sent – ${baby.givenName}'s parents can now see your update.")
            }
        }
    }

    CradleScreenScaffold(
        title = "Care Team",
        subtitle = "Send an update to parents",
        modifier = modifier,
        snackbarHostState = snackbarHostState,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .imePadding()
                .verticalScroll(rememberScrollState())
                .padding(Dimens.ScreenPadding),
            verticalArrangement = Arrangement.spacedBy(Dimens.SectionSpacing),
        ) {
            RecipientPicker(
                babies = babies,
                selectedId = recipientId,
                onSelect = { recipientId = it },
            )

            TemplateRow(onApply = { draft = draft.applying(it) })

            MessageFields(draft = draft, onChange = { draft = it })

            StatusChangeFields(draft = draft, onChange = { draft = it })

            Column(verticalArrangement = Arrangement.spacedBy(Dimens.ItemSpacing)) {
                Button(
                    onClick = ::send,
                    enabled = canSend,
                    shapes = ButtonDefaults.shapes(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = ButtonDefaults.MediumContainerHeight),
                ) {
                    Icon(painter = painterResource(R.drawable.ic_send), contentDescription = null, modifier = Modifier.size(24.dp))
                    Spacer(Modifier.width(8.dp))
                    Text("Send to Parents", style = MaterialTheme.typography.titleMedium)
                }
                Text(
                    "Parents are notified straight away. Please avoid medical jargon, and call parents for anything urgent.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}
