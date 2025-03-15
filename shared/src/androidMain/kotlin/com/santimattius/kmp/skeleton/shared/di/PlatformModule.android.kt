package com.santimattius.kmp.skeleton.shared.di

import com.santimattius.kmp.skeleton.shared.ui.CharacterViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

actual val platformModule: Module
    get() = module {
        viewModelOf(::CharacterViewModel)
    }