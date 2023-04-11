package com.example.munch

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class InstructionResponse(
    @SerialName("")
    val instructions: List<Instructions>?
)

@Keep
@Serializable
data class Instructions(
    @SerialName("number")
    val number: Int,
    @SerialName("step")
    val step: String
)