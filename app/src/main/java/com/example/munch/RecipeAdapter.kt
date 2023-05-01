package com.example.munch

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

//const val RECIPE_EXTRA = "RECIPE_EXTRA"
private const val TAG = "RecipeAdapter"

class RecipeAdapter(private val context: Context, private val recipes: List<Recipe>) :
    RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recycler, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.bind(recipe)
    }

    override fun getItemCount() = recipes.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        private val recipeImageView = itemView.findViewById<ImageView>(R.id.recipeImage)
        private val titleTextView = itemView.findViewById<TextView>(R.id.title)
        private val instructionsTextView = itemView.findViewById<TextView>(R.id.instructions)
        private val ingredientsTextView = itemView.findViewById<TextView>(R.id.ingredients)


        init {
            itemView.setOnClickListener(this)
        }
        fun bind(recipe: Recipe) {
            titleTextView.text = recipe.title
            ingredientsTextView.text = recipe.ingredients.toString()
            //instructionsTextView.text = recipe.abstract


            Glide.with(context)
                .load(recipe.image)
                .into(recipeImageView)
        }
        
        override fun onClick(v: View?) {
            //val recipe = recipes[absoluteAdapterPosition]
            //val intent = Intent(context, DetailActivity::class.java)
            //intent.putExtra(RECIPE_EXTRA, recipe)
            //context.startActivity(intent)
        }


    }
}