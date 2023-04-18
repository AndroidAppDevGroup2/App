package com.example.munch

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.munch.ui.SearchFragment

private const val TAG = "SearchAdapter"

class SearchAdapter(private val context: Context, private val recipes: List<Recipe>) :
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recipe_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.bind(recipe)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val recipeTitle: TextView = itemView.findViewById(R.id.recipeTitleTextView)
        private val recipeImage: ImageView = itemView.findViewById(R.id.recipeMainImageView)
        private val saveButton: Button = itemView.findViewById(R.id.saveRecipeButton)
        fun bind(recipe: Recipe) {
            recipeTitle.text = recipe.title
            Glide.with(context)
                .load(recipe.image)
                .into(recipeImage)
        }

    }

    override fun getItemCount() = recipes.size

}