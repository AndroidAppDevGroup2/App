package com.example.munch

import android.support.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class Recipe(
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("usedIngredients")
    val ingredients: List<Ingredient>
)  : java.io.Serializable

data class Ingredient(
    @SerializedName("name")
    val name: String
) : java.io.Serializable