package com.santimattius.kmp.skeleton.shared.ui

import com.santimattius.kmp.skeleton.shared.domain.Character

sealed interface CharacterUiState {
    data object Loading : CharacterUiState
    data class Success(val characters: List<Character>) : CharacterUiState
    data class Error(val message: String) : CharacterUiState
}