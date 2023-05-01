package com.example.munch

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DetailActivity : AppCompatActivity() {
    private lateinit var recipeImageView: ImageView
    private lateinit var titleTextView: TextView
    private lateinit var instructionsTextView: TextView
    private lateinit var ingredientsTextView: TextView
    private lateinit var saveButton: Button
    private lateinit var  dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_screen)


        recipeImageView = findViewById(R.id.recipeImage)
        titleTextView = findViewById(R.id.title)
        instructionsTextView = findViewById(R.id.ingredients)
        ingredientsTextView = findViewById(R.id.instructions)
        saveButton = findViewById(R.id.button)
        dbRef = FirebaseDatabase.getInstance().getReference("Recipes")
        val recipe = intent.getSerializableExtra(RECIPE_EXTRA) as Recipe

        titleTextView.text = recipe.title
        ingredientsTextView.text = recipe.ingredients.toString()
        //ingredientsTextView.text = recipe.abstract

        Glide.with(this)
            .load(recipe.image)
            .into(recipeImageView)

        saveButton.setOnClickListener {
            saveRecipeData(recipe)
        }
    }

    private fun saveRecipeData(recipe: Recipe){
        val recipeId = dbRef.push().key!!
        dbRef.child(recipeId).setValue(recipe)
            .addOnCompleteListener {
                Toast.makeText(this, "Recipe Saved Successfully", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener { err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_SHORT).show()
            }
    }
}