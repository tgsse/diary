package com.ix.diary.viewmodel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class EntryState(
    val isLoading: Boolean = true,
)

sealed class EntryEvent {
//    object Init : DiaryEvent()
    object Cancel: EntryEvent()
    object Save: EntryEvent()
}

//@HiltViewModel
class EntryViewModel {
    private val _state = MutableStateFlow(EntryState())
    val state = _state.asStateFlow()

    fun onEvent(event: EntryEvent) {
        when (event) {
            is EntryEvent.Save -> cancel()
            is EntryEvent.Cancel -> save()
        }
    }

    private fun cancel() {
        // TODO()
    }

    private fun save() {
        // TODO()
    }
}