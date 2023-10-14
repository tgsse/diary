package com.ix.diary.viewmodel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class DiaryState(
    val isLoading: Boolean = true,
)

sealed class DiaryEvent {
//    object Init : DiaryEvent()
    object Create: DiaryEvent()
    object Edit: DiaryEvent()
}

//@HiltViewModel
class DiaryViewModel {
    private val _state = MutableStateFlow(DiaryState())
    val state = _state.asStateFlow()

    fun onEvent(event: DiaryEvent) {
        when (event) {
            is DiaryEvent.Create -> authenticate()
            is DiaryEvent.Edit -> edit()
        }
    }

    private fun authenticate() {
        // TODO()
    }

    private fun edit() {
        // TODO()
    }
}