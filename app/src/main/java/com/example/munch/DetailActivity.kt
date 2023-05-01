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
        instructionsTextView = findViewById(R.id.ingredients)
        ingredientsTextView = findViewById(R.id.instructions)

        val recipe = intent.getSerializableExtra(RECIPE_EXTRA) as Recipe

        titleTextView.text = recipe.title
        ingredientsTextView.text = recipe.ingredients.toString()
        //ingredientsTextView.text = recipe.abstract

        Glide.with(this)
            .load(recipe.image)
            .apply(RequestOptions().override(1200, 400))
            .into(recipeImageView)
    }
}