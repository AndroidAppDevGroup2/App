package com.example.munch

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Headers
import org.json.JSONArray


private const val TAG = "DetailActivity"
private const val SEARCH_API_KEY = BuildConfig.API_KEY

class DetailActivity : AppCompatActivity() {
    private lateinit var recipeImageView: ImageView
    private lateinit var titleTextView: TextView
    private lateinit var instructionsTextView: TextView
    private lateinit var ingredientsTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        val client = AsyncHttpClient()

        // Using the client, perform the HTTP request

        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_screen)


        recipeImageView = findViewById(R.id.recipeImage)
        titleTextView = findViewById(R.id.title)
        instructionsTextView = findViewById(R.id.instructions)
        ingredientsTextView = findViewById(R.id.ingredients)

        val recipe = intent.getSerializableExtra(RECIPE_EXTRA) as Recipe
        val id=recipe.id
        print(id)
        client[
                "https://api.spoonacular.com/recipes/${id}/analyzedInstructions?apiKey=${SEARCH_API_KEY}",
                object : JsonHttpResponseHandler()
                {
                    /*
                     * The onSuccess function gets called when
                     * HTTP response status is "200 OK"
                     */
                    override fun onSuccess(
                        statusCode: Int,
                        headers: Headers,
                        json: JSON
                    ) {
                        // The wait for a response is over


                        val resultsJSON : JSONArray = json.jsonArray
                        Log.v("JSONArray", resultsJSON.toString())
                        val recipesRawJSON : String = resultsJSON.toString()
                        val gson = Gson()
                        val arrayTutorialType = object : TypeToken<List<Steps>>() {}.type
                        val models : List<Steps> = gson.fromJson(recipesRawJSON, arrayTutorialType)
                        if (models.isEmpty()){
                            instructionsTextView.text="no instructions found :("
                        }
                        else {
                            var instructions=models.joinToString(separator = "\n")
                            instructions=instructions.replace("Steps(instructions=[Instructions(number=","")
                            instructions=instructions.replace("Instructions(number=","\n")
                            instructions=instructions.replace("step=","")
                            instructions=instructions.replace(")","")
                            instructions=instructions.replace("]","")
                            instructionsTextView.text = instructions
                        }
                        // Look for this in Logcat:
                        Log.d("BestSellerBooksFragment", "response successful")
                    }

                    /*
                     * The onFailure function gets called when
                     * HTTP response status is "4XX" (eg. 401, 403, 404)
                     */
                    override fun onFailure(
                        statusCode: Int,
                        headers: Headers?,
                        errorResponse: String,
                        t: Throwable?
                    ) {
                        // The wait for a response is over


                        // If the error is not null, log it!
                        t?.message?.let {
                            Log.e("BestSellerBooksFragment", errorResponse)
                        }
                    }
                }]

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