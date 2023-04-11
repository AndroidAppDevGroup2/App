package com.example.munch

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class RecipeResponse(
    @SerialName("")
    val recipes: List<Recipe>?
)

@Keep
@Serializable
data class Recipe(
    @SerialName("id")
    val id: String,
    @SerialName("image")
    val image: String,
    @SerialName("title")
    val title: String,
    @SerialName("usedIngredients")
    val ingredients: List<Ingredient>
)

@Keep
@Serializable
data class Ingredient(
    @SerialName("name")
    val name: String
)