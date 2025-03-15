package com.santimattius.kmp.skeleton.shared.domain

import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName


data class Character @OptIn(ExperimentalObjCName::class) constructor(
    val id: Long,
    val name: String,
    val image: String,
    @ObjCName(name = "desc") val description: String,
)