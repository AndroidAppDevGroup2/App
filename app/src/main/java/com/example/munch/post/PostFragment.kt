package com.example.munch.post
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.munch.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.io.ByteArrayOutputStream

class PostFragment : Fragment() {

    private lateinit var titleEditText: EditText
    private lateinit var imageView: ImageView
    private lateinit var takePictureButton: Button
    private lateinit var postButton: Button
    private var imageBitmap: Bitmap? = null

    private lateinit var databaseReference: DatabaseReference

    private val takePicture =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                imageBitmap = result.data?.extras?.get("data") as? Bitmap
                imageView.setImageBitmap(imageBitmap)
            } else {
                Toast.makeText(requireContext(), "Failed to capture image.", Toast.LENGTH_SHORT)
                    .show()
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        titleEditText = view.findViewById(R.id.titleEditText)
        imageView = view.findViewById(R.id.imageView)
        takePictureButton = view.findViewById(R.id.takePictureButton)
        postButton = view.findViewById(R.id.postButton)

        databaseReference = FirebaseDatabase.getInstance().getReference("posts")

        takePictureButton.setOnClickListener {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            takePicture.launch(takePictureIntent)
        }

        postButton.setOnClickListener {
            val title = titleEditText.text.toString().trim()
            if (title.isNotEmpty() && imageBitmap != null) {
                savePost(title, imageBitmap!!)
            } else {
                Toast.makeText(requireContext(), "Please enter a title and capture an image.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun savePost(title: String, imageBitmap: Bitmap) {
        val postId = databaseReference.push().key
        if (postId != null) {
            val baos = ByteArrayOutputStream()
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            val imageData = baos.toByteArray()
            val encodedImage = android.util.Base64.encodeToString(imageData, android.util.Base64.DEFAULT)

            val post = hashMapOf(
                "id" to postId,
                "title" to title,
                "image" to encodedImage
            )

            databaseReference.child(postId).setValue(post)
                .addOnSuccessListener {
                    Toast.makeText(requireContext(), "Post saved successfully.", Toast.LENGTH_SHORT).show()
                    titleEditText.text.clear()
                    imageView.setImageBitmap(null)
                    this.imageBitmap = null;
                }
                .addOnFailureListener {
                    Toast.makeText(requireContext(), "Failed to save post. Please try again.", Toast.LENGTH_SHORT).show()
                }
        } else {
            Toast.makeText(requireContext(), "Failed to generate post ID. Please try again.", Toast.LENGTH_SHORT).show()
        }
    }
}

