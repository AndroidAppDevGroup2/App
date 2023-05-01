package com.example.munch

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions




private const val TAG = "DetailActivity"
class DetailActivity : AppCompatActivity() {
    private lateinit var recipeImageView: ImageView
    private lateinit var titleTextView: TextView
    private lateinit var instructionsTextView: TextView
    private lateinit var ingredientsTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_screen)


        recipeImageView = findViewById(R.id.recipeImage)
        titleTextView = findViewById(R.id.title)
        instructionsTextView = findViewById(R.id.instructions)
        ingredientsTextView = findViewById(R.id.ingredients)

        val recipe = intent.getSerializableExtra(RECIPE_EXTRA) as Recipe
        titleTextView.text = recipe.title

        var LOI=recipe.ingredients.joinToString(separator = "\n")+"\n"+ recipe.ingredients2.joinToString(separator = "\n")
        LOI=LOI.replace("Ingredient(name=","")
        LOI=LOI.replace(")","")
        LOI=LOI.replace("(","")
        println(LOI)
        ingredientsTextView.text = LOI

        //ingredientsTextView.text = recipe.abstract

        Glide.with(this)
            .load(recipe.image)
            .apply(RequestOptions().override(1200, 400))
            .into(recipeImageView)
    }
}