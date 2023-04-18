package com.example.munch

import com.google.gson.annotations.SerializedName

class Recipe(
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("usedIngredients")
    val ingredients: List<Ingredient>
)

class Ingredient(
    @SerializedName("name")
    val name: String
)