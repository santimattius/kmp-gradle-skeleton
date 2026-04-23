package com.santimattius.kmp.skeleton.shared.di

import org.koin.core.module.Module

expect val platformModule: Module

fun sharedModule() = listOf(platformModule, sharedDataModule)
