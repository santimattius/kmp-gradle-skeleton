package com.santimattius.kmp.skeleton.shared.di

import org.koin.core.module.Module
import org.koin.ksp.generated.com_santimattius_kmp_skeleton_shared_di_SharedModule

expect val platformModule: Module

fun sharedModule() = listOf(platformModule, com_santimattius_kmp_skeleton_shared_di_SharedModule)