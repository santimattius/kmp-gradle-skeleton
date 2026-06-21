package com.santimattius.kmp.skeleton.shared.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.santimattius.kmp.skeleton.shared.domain.CharacterRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.reflect.KClass

class CharacterViewModelFactory : ViewModelProvider.Factory, KoinComponent {
    private val repository: CharacterRepository by inject()
    override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
        return CharacterViewModel(repository) as T
    }
}