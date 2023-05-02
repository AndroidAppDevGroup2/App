package com.example.munch.saved

data class Recipe(
    val id: String,
    val title: String,
    val image: String,
    val ingredients: List<String>,
    val ingredients2: List<String>
)
