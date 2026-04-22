package com.santimattius.kmp.skeleton.shared.di

import com.santimattius.kmp.skeleton.shared.data.DragonBallCharacterRepository
import com.santimattius.kmp.skeleton.shared.data.client.BASE_URL
import com.santimattius.kmp.skeleton.shared.data.client.ktorHttpClient
import com.santimattius.kmp.skeleton.shared.domain.CharacterRepository
import org.koin.dsl.module

val sharedDataModule = module {
    single { ktorHttpClient(baseUrl = BASE_URL) }
    single<CharacterRepository> { DragonBallCharacterRepository(get()) }
}
