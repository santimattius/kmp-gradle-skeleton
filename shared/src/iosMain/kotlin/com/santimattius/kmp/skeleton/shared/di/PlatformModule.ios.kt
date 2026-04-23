package com.santimattius.kmp.skeleton.shared.di

import com.santimattius.kmp.skeleton.shared.ui.CharacterViewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.plugin.module.dsl.factory

actual val platformModule: Module
    get() = module {
        factory<CharacterViewModel>()
    }
