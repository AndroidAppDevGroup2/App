package com.example.munch

import com.google.gson.annotations.SerializedName

class Instructions(
    @SerializedName("number")
    val number: Int,
    @SerializedName("step")
    val step: String
)