package com.example.cradle.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cradle.model.UpdateDraft
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NurseryViewModel(
    private val store: NurseryStore = NurseryStore(),
) : ViewModel() {
    val state: StateFlow<NurseryState> = store.state

    fun select(babyId: String) = store.select(babyId)

    fun refresh() {
        viewModelScope.launch { store.refresh() }
    }

    fun send(draft: UpdateDraft, babyId: String): Boolean = store.send(draft, babyId)

    fun markUpdatesRead() = store.markUpdatesRead()
}
