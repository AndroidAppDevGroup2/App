package com.example.munch

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.codepath.articlesearch.ARTICLE_EXTRA

class DetailActivity : AppCompatActivity() {
    private lateinit var mediaImageView: ImageView
    private lateinit var titleTextView: TextView
    private lateinit var bylineTextView: TextView
    private lateinit var abstractTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_screen)


        mediaImageView = findViewById(R.id.recipeImage)
        titleTextView = findViewById(R.id.title)
        bylineTextView = findViewById(R.id.ingredients)
        abstractTextView = findViewById(R.id.instructions)

        val article = intent.getSerializableExtra(ARTICLE_EXTRA) as Recipe

//        titleTextView.text = article.Ingredient?.name
//        bylineTextView.text = article.Recipe?.original
//        abstractTextView.text = article.abstract
//
//        Glide.with(this)
//            .load(article.mediaImageUrl)
//            .into(mediaImageView)
    }
}