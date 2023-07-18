package com.bamboogeeks.julyacademy2.firebasestorageapp.ui

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bamboogeeks.julyacademy2.R
import com.bamboogeeks.julyacademy2.databinding.ActivityFirebaseStorageAppBinding
import com.bamboogeeks.julyacademy2.firebasestorageapp.utils.FirebaseImagesAdapter
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.lang.Exception


private const val REQUEST_CODE_IMAGE_PICKER = 0

@Suppress("DEPRECATION")
class FirebaseStorageApp : AppCompatActivity() {
    private lateinit var binding: ActivityFirebaseStorageAppBinding
    var currentImage: Uri? = null
    val storageReference = Firebase.storage.reference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirebaseStorageAppBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.ivSelectImage.setOnClickListener {
            Intent(Intent.ACTION_GET_CONTENT).also {
                it.type = "image/*"
                startActivityForResult(it, REQUEST_CODE_IMAGE_PICKER)
            }
        }
        binding.btnUploadImage.setOnClickListener {
            uploadImageToFirebaseStorage("My Image")
        }
        binding.btnDownloadImage.setOnClickListener {
            downloadImageFromFirebaseStorage("My Image")
        }
        binding.btnDeleteImage.setOnClickListener {
            deleteImageFromFirebaseStorage("My Image")
        }
        listAllImagesFromFirebaseStorage();

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_IMAGE_PICKER) {
            data?.data.let {
                currentImage = it
                binding.ivSelectImage.setImageURI(it)
            }
        }
    }
    private fun uploadImageToFirebaseStorage(fileName: String) {

        CoroutineScope(Dispatchers.IO).launch {
            try {
                currentImage?.let {
                    storageReference.child("images/$fileName").putFile(it).await() // Upload Image
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@FirebaseStorageApp,
                            "Image Uploaded Succesffully",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }


            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@FirebaseStorageApp, e.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
    private fun downloadImageFromFirebaseStorage(fileName: String) {

        CoroutineScope(Dispatchers.IO).launch {
            try {

                val maxDownloadSizeByte = 5L * 1024 * 1024
                val imageByte =
                    storageReference.child("images/$fileName").getBytes(maxDownloadSizeByte).await()
                val imageBitMap = BitmapFactory.decodeByteArray(imageByte, 0, imageByte.size)
                withContext(Dispatchers.Main) {
                    binding.ivSelectImage.setImageBitmap(imageBitMap)
                    Toast.makeText(
                        this@FirebaseStorageApp,
                        "Image Downloaded Succesffully",
                        Toast.LENGTH_LONG
                    ).show()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@FirebaseStorageApp, e.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
    private fun deleteImageFromFirebaseStorage(fileName: String) {

        CoroutineScope(Dispatchers.IO).launch {
            try {
                storageReference.child("images/$fileName").delete().await()

                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@FirebaseStorageApp,
                        "Image Deleted Successfully",
                        Toast.LENGTH_LONG
                    ).show()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@FirebaseStorageApp, e.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
    private fun listAllImagesFromFirebaseStorage() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val allImages = storageReference.child("images/").listAll().await()
                val imageUrls = mutableListOf<String>()
                for (singleImage in allImages.items){
                   val singleImageUrl = singleImage.downloadUrl.await()
                    imageUrls.add(singleImageUrl.toString())
                }

                withContext(Dispatchers.Main) {
                   val firebaseImageAdapter = FirebaseImagesAdapter(imageUrls)
                    binding.rvFirebaseImages.apply {
                        adapter = firebaseImageAdapter
                        layoutManager = LinearLayoutManager(this@FirebaseStorageApp)
                    }

                    Toast.makeText(
                        this@FirebaseStorageApp,
                        "Image Loaded Successfully",
                        Toast.LENGTH_LONG
                    ).show()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@FirebaseStorageApp, e.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

}