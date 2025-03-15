package com.santimattius.kmp.skeleton.shared.domain

interface CharacterRepository {

    suspend fun getCharacters(): Result<List<Character>>
}