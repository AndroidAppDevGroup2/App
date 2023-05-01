package com.example.munch

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
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
    private lateinit var saveButton: Button
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        val client = AsyncHttpClient()

        // Using the client, perform the HTTP request

        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_screen)


        recipeImageView = findViewById(R.id.recipeImage)
        titleTextView = findViewById(R.id.title)
        instructionsTextView = findViewById(R.id.instructions)
        ingredientsTextView = findViewById(R.id.ingredients)
        saveButton = findViewById(R.id.button)
        dbRef = FirebaseDatabase.getInstance().getReference("Recipes")
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
                        val arrayTutorialType = object : TypeToken<List<Instructions>>() {}.type
                        val models : List<Instructions> = gson.fromJson(recipesRawJSON, arrayTutorialType)
                        if (models.isEmpty()){
                            instructionsTextView.text="no instructions found :("
                        }
                        else {
                            var instructions=""
                            for (each in models){
                                instructions=each.number.toString()+" "+each.step
                            }
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