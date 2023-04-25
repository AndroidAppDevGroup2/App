package com.example.munch.Post

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.munch.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.UUID


class PostActivity : AppCompatActivity() {

   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_post)

        // Call uploadImageAndGetUrl() when the user submits a post
        // (e.g., when a button is clicked)

       // *** Uncomment this when we have a post button! ***

//        postButton.setOnClickListener {
//            uploadImageAndGetUrl(imageUri) { imageUrl ->
//                createPost(title, imageUrl)
//            }
//        }
    }

    private fun uploadImageAndGetUrl(imageUri: Uri, onSuccess: (String) -> Unit) {
        val storageRef = FirebaseStorage.getInstance().reference
        val imageRef = storageRef.child("images/${UUID.randomUUID()}")

        imageRef.putFile(imageUri)
            .addOnSuccessListener {
                imageRef.downloadUrl.addOnSuccessListener { downloadUrl ->
                    onSuccess(downloadUrl.toString())
                }
            }
            .addOnFailureListener {
                // Handle failure
            }
    }

    private fun createPost(title: String, imageUrl: String) {
        val post = Post(title, imageUrl)
        val database = FirebaseDatabase.getInstance()
        val postsRef = database.getReference("posts")
        val postId = postsRef.push().key ?: return
        postsRef.child(postId).setValue(post)
    }

    private fun loadPosts() {
        val database = FirebaseDatabase.getInstance()
        val postsRef = database.getReference("posts")

        postsRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val posts = mutableListOf<Post>()
                for (postSnapshot in dataSnapshot.children) {
                    val post = postSnapshot.getValue(Post::class.java)
                    if (post != null) {
                        posts.add(post)
                    }
                }
                // *** Update UI with the new list of posts ***
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle errors
            }
        })
    }


}
