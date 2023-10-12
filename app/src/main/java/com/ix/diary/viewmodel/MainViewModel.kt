package com.ix.diary.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ix.diary.data.feature.FeatureRepositoryI
import com.ix.diary.data.feature.local.FeatureEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

data class MainState(
    val isLoading: Boolean = true,
    val featureEntity: FeatureEntity? = null,
)

sealed class MainEvent {
    object Init : MainEvent()
}

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: FeatureRepositoryI,
) : ViewModel() {

    private val uiEventChannel = Channel<UiEvent>()
    val uiEvents = uiEventChannel.receiveAsFlow()

    private val _state = MutableStateFlow(MainState())
    val state = _state.asStateFlow()

    fun onEvent(event: MainEvent) {
        when (event) {
            is MainEvent.Init -> init()
        }
    }

    private fun init() {
        Timber.d("Main ViewModel init")
        viewModelScope.launch {
            try {
                val data = repository.local.load()
                if (data == null) {
                    repository.remote.fetch()?.let {
                        _state.update { s -> s.copy(featureEntity = it) }
                        repository.local.save(it)
                    }
                } else {
                    _state.update { s -> s.copy(featureEntity = data) }
                }
            } catch (e: Exception) {
                Timber.e(e)
            } finally {
                _state.update { s -> s.copy(isLoading = false) }
            }
        }
    }

    sealed class UiEvent {
        data class ShowMessage(val message: String?) : UiEvent()
    }
}
