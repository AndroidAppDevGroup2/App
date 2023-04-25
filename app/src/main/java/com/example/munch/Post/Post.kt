package com.example.munch.Post

import android.net.Uri
import com.google.firebase.storage.FirebaseStorage
import java.util.UUID

data class Post(val title: String = "", val imageUrl: String = "")

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
