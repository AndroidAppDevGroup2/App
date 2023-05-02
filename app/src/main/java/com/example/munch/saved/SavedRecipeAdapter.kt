package com.example.munch.saved
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.munch.DetailActivity
import com.example.munch.R
import com.example.munch.RECIPE_EXTRA
import com.example.munch.Recipe
class SavedRecipeAdapter(private val context: Context, private val recipeList: List<Recipe>) :
    RecyclerView.Adapter<SavedRecipeAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val titleTextView: TextView = itemView.findViewById(R.id.recipe_title)
        val imageView: ImageView = itemView.findViewById(R.id.recipe_image)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            // Get selected article
            val recipe = recipeList[absoluteAdapterPosition]

            //  Navigate to SavedDetails screen and pass selected article
            val intent = Intent(context, SavedDetailActivity::class.java)
            intent.putExtra(RECIPE_EXTRA, recipe)
            context.startActivity(intent)
        }


    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.saved_recipe_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe = recipeList[position]
        holder.titleTextView.text = recipe.title
        Glide.with(holder.itemView.context)
            .load(recipe.image)
            .into(holder.imageView)
    }

    override fun getItemCount() = recipeList.size
}
