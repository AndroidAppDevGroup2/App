
package com.codepath.articlesearch

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

const val ARTICLE_EXTRA = "ARTICLE_EXTRA"
private const val TAG = "RecipeAdapter"

class RecipeAdapter(private val context: Context, private val articles: List<DisplayArticle>) :
    RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recycler, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = articles[position]
        holder.bind(article)
    }

    override fun getItemCount() = articles.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        private val recipeImageView = itemView.findViewById<ImageView>(R.id.recipeImage)
        private val titleTextView = itemView.findViewById<TextView>(R.id.title)
        private val instructionsTextView = itemView.findViewById<TextView>(R.id.instructions)
        private val ingredientsTextView = itemView.findViewById<TextView>(R.id.ingredients)


        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val article = articles[absoluteAdapterPosition]
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(ARTICLE_EXTRA, article)
            context.startActivity(intent)
        }

        fun bind(article: DisplayArticle) {
            titleTextView.text = article.headline
            ingredientsTextView.text = article.abstract
            instructionsTextView.text = article.abstract


            Glide.with(context)
                .load(article.mediaImageUrl)
                .into(recipeImageView)
        }
    }
}