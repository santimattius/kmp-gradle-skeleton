package com.santimattius.kmp.skeleton.shared.domain

import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName


@OptIn(ExperimentalObjCName::class)
data class Character(
    val id: Long,
    val name: String,
    val image: String,
    @ObjCName(name = "desc") val description: String,
)