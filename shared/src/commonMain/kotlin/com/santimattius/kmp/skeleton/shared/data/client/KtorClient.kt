package com.santimattius.kmp.skeleton.shared.data.client

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import co.touchlab.kermit.Logger as KermitLogger

const val BASE_URL = "https://dragonball-api.com"

internal fun ktorHttpClient(baseUrl: String) = HttpClient {

    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        })
    }
    install(Logging) {
        logger = object : Logger {
            override fun log(message: String) {
                KermitLogger.i("KtorHttpClient") { message }
            }
        }
        level = LogLevel.ALL
    }

    defaultRequest {
        url(baseUrl)
        contentType(ContentType.Application.Json)
    }
}