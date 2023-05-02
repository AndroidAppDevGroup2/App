package com.example.munch

import android.support.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class Steps(
    @SerializedName("steps")
    val instructions: List<Instructions>
) : java.io.Serializable
@Serializable
data class Instructions(
    @SerializedName("number")
    val number: Int,
    @SerializedName("step")
    val step: String,
) : java.io.Serializable