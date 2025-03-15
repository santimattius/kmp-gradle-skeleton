package com.santimattius.kmp.skeleton.shared.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import com.santimattius.kmp.skeleton.shared.domain.CharacterRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharacterViewModel(
    private val repository: CharacterRepository
) : ViewModel() {

    private val _state = MutableStateFlow<CharacterUiState>(CharacterUiState.Loading)
    val state = _state.onStart {
        fetch()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = CharacterUiState.Loading
    )

    private val exceptionHandler = CoroutineExceptionHandler { _, ex ->
        Logger.e(ex) { ex.message.orEmpty() }
        _state.update { CharacterUiState.Error("Unknown error") }
    }

    private fun fetch() {
        _state.update { CharacterUiState.Loading }
        viewModelScope.launch(exceptionHandler) {
            repository.getCharacters().onFailure { ex ->
                Logger.e(ex) { ex.message.orEmpty() }
                _state.update { CharacterUiState.Error(ex.message ?: "Unknown error") }
            }.onSuccess { characters ->
                _state.update {
                    CharacterUiState.Success(characters)
                }
            }
        }
    }
}