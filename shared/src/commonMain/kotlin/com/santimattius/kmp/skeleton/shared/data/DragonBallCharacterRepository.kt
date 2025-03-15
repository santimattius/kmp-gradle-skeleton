package com.santimattius.kmp.skeleton.shared.data

import com.santimattius.kmp.skeleton.shared.domain.CharacterRepository
import com.santimattius.kmp.skeleton.shared.data.model.CharacterResponse
import com.santimattius.kmp.skeleton.shared.domain.Character
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class DragonBallCharacterRepository(
    private val httpClient: HttpClient,
) : CharacterRepository {

    override suspend fun getCharacters(): Result<List<Character>> {
        return runCatching {
            httpClient.get("/api/characters")
                .body<CharacterResponse>().items.map {
                    Character(
                        id = it.id,
                        name = it.name,
                        image = it.image,
                        description = it.description,
                    )
                }
        }
    }
}