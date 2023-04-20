package com.example.munch.ui

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.munch.BuildConfig
import com.example.munch.R
import com.example.munch.Recipe
import com.example.munch.SearchAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Headers
import org.json.JSONArray

import org.json.JSONObject

// Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

private const val SEARCH_API_KEY = BuildConfig.API_KEY
private const val RECIPE_SEARCH_URL = "https://api.spoonacular.com/recipes/findByIngredients?apiKey=${SEARCH_API_KEY}&ingredients="

/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment() {
    private val recipes = mutableListOf<Recipe>()
    private lateinit var searchRecyclerView: RecyclerView
    private lateinit var searchAdapter: SearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        val searchButton: Button = view.findViewById(R.id.searchButton)
        val searchBar: EditText = view.findViewById(R.id.searchBar)
        val layoutManager = LinearLayoutManager(context)
        searchRecyclerView = view.findViewById(R.id.searchRecyclerView)
        searchRecyclerView.layoutManager = layoutManager
        searchRecyclerView.setHasFixedSize(true)
        searchButton.setOnClickListener {
            updateAdapter(searchRecyclerView, view)
            searchBar.text.clear()
            hideKeyboard()
        }
        searchAdapter = SearchAdapter(view.context, recipes)
        searchRecyclerView.adapter = searchAdapter
        return view
    }

    private fun updateAdapter( recyclerView: RecyclerView, view: View) {
        val searchBar: TextView = view.findViewById(R.id.searchBar)

        // Create and set up an AsyncHTTPClient() here
        val client = AsyncHttpClient()

        // Using the client, perform the HTTP request
        client[
                RECIPE_SEARCH_URL + searchBar.text,
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
                        val arrayTutorialType = object : TypeToken<List<Recipe>>() {}.type
                        val models : List<Recipe> = gson.fromJson(recipesRawJSON, arrayTutorialType)
                        recyclerView.adapter = SearchAdapter(view.context , models)

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
    }

    companion object {
        fun newInstance(): SearchFragment {
            return SearchFragment()
        }
    }
    fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }
    fun Activity.hideKeyboard() {
        hideKeyboard(currentFocus ?: View(this))
    }
    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}