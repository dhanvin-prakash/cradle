package com.example.cradle.ui.careteam

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import com.example.cradle.R
import com.example.cradle.model.UpdateDraft
import com.example.cradle.model.UpdateKind
import com.example.cradle.ui.components.SectionHeader
import com.example.cradle.ui.theme.Dimens

@Composable
fun MessageFields(
    draft: UpdateDraft,
    onChange: (UpdateDraft) -> Unit,
) {
    val fieldShape = MaterialTheme.shapes.large

    Column(verticalArrangement = Arrangement.spacedBy(Dimens.ItemSpacing)) {
        SectionHeader(title = "Message", icon = R.drawable.ic_chat)

        DropdownField(
            label = "Type",
            selected = draft.kind,
            options = UpdateKind.entries,
            optionLabel = { it.title },
            optionIcon = { it.icon },
            onSelect = { onChange(draft.copy(kind = it)) },
        )

        OutlinedTextField(
            value = draft.title,
            onValueChange = { onChange(draft.copy(title = it)) },
            label = { Text("Headline") },
            placeholder = { Text("e.g. “Finished a full feed”") },
            singleLine = true,
            shape = fieldShape,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
                imeAction = ImeAction.Next,
            ),
            modifier = Modifier.fillMaxWidth(),
        )

        OutlinedTextField(
            value = draft.detail,
            onValueChange = { onChange(draft.copy(detail = it)) },
            label = { Text("What would you like the parents to know?") },
            minLines = 3,
            shape = fieldShape,
            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
            modifier = Modifier.fillMaxWidth(),
        )

        OutlinedTextField(
            value = draft.author,
            onValueChange = { onChange(draft.copy(author = it)) },
            label = { Text("Signed by") },
            placeholder = { Text("e.g. “Nurse Siti”") },
            supportingText = { Text("Remembered for your next update") },
            singleLine = true,
            shape = fieldShape,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Done,
            ),
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
