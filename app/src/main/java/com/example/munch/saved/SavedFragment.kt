package com.example.munch.saved

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.munch.R
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class SavedFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var noSavedText: TextView
    private lateinit var recipeDatabase: DatabaseReference
    private lateinit var savedRecipeAdapter: SavedRecipeAdapter
    private val recipeList = mutableListOf<Recipe>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_saved, container, false)
        recyclerView = view.findViewById(R.id.saved_recycler_view)
        noSavedText = view.findViewById(R.id.no_saved_text)

        recyclerView.layoutManager = LinearLayoutManager(context)
        savedRecipeAdapter = SavedRecipeAdapter(recipeList)
        recyclerView.adapter = savedRecipeAdapter

        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            recipeDatabase = FirebaseDatabase.getInstance().getReference("Recipes").child(userId)

            fetchSavedRecipes()
        } else {
            noSavedText.visibility = View.VISIBLE
        }

        return view
    }

    private fun fetchSavedRecipes() {
        recipeDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                recipeList.clear()
                for (recipeSnapshot in snapshot.children) {
                    val id = recipeSnapshot.child("id").getValue(String::class.java)
                    val title = recipeSnapshot.child("title").getValue(String::class.java)
                    val image = recipeSnapshot.child("image").getValue(String::class.java)

                    val ingredients = mutableListOf<String>()
                    for (ingredientSnapshot in recipeSnapshot.child("ingredients").children) {
                        val ingredientName = ingredientSnapshot.child("name").getValue(String::class.java)
                        if (ingredientName != null) {
                            ingredients.add(ingredientName)
                        }
                    }

                    val ingredients2 = mutableListOf<String>()
                    for (ingredientSnapshot in recipeSnapshot.child("ingredients2").children) {
                        val ingredientName = ingredientSnapshot.child("name").getValue(String::class.java)
                        if (ingredientName != null) {
                            ingredients2.add(ingredientName)
                        }
                    }

                    if (id != null && title != null && image != null) {
                        val recipe = Recipe(id, title, image, ingredients, ingredients2)
                        recipeList.add(recipe)
                    }
                }

                if (recipeList.isEmpty()) {
                    noSavedText.visibility = View.VISIBLE
                } else {
                    noSavedText.visibility = View.GONE
                }

                savedRecipeAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle errors here
            }
        })
    }
}
