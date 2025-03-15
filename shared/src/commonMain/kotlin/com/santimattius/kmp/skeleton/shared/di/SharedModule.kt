package com.santimattius.kmp.skeleton.shared.di

import com.santimattius.kmp.skeleton.shared.data.DragonBallCharacterRepository
import com.santimattius.kmp.skeleton.shared.data.client.BASE_URL
import com.santimattius.kmp.skeleton.shared.data.client.ktorHttpClient
import com.santimattius.kmp.skeleton.shared.domain.CharacterRepository
import io.ktor.client.HttpClient
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
class SharedModule {

    @Single
    fun provideCharacterRepository(httpClient: HttpClient): CharacterRepository {
        return DragonBallCharacterRepository(httpClient)
    }

    @Single
    fun provideHttpClient(): HttpClient {
        return ktorHttpClient(baseUrl = BASE_URL)
    }

}