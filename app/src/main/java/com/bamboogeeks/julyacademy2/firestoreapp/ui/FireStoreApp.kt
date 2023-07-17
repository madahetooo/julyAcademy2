package com.bamboogeeks.julyacademy2.firestoreapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bamboogeeks.julyacademy2.R
import com.bamboogeeks.julyacademy2.auth.model.ProfileInformation
import com.bamboogeeks.julyacademy2.databinding.ActivityFireStoreAppBinding
import com.bamboogeeks.julyacademy2.firestoreapp.model.ProfileDetails
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.lang.StringBuilder

class FireStoreApp : AppCompatActivity() {
    private lateinit var binding: ActivityFireStoreAppBinding
    private val profileDetailsCollectionRef = Firebase.firestore.collection("ProfileDetails")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFireStoreAppBinding.inflate(layoutInflater)
        setContentView(binding.root)
        subscribeToRealTimeUpdates()
        binding.btnSaveProfileDetails.setOnClickListener {
            val oldProfileDetails = getOldProfileDetails()
            saveProfileDetails(oldProfileDetails)
        }
        binding.btnReadProfileDetails.setOnClickListener {
            readProfileDetailsData()
        }
        binding.btnDeleteProfileDetails.setOnClickListener {
            val oldProfileDetails = getOldProfileDetails()
            deleteProfileDetails(oldProfileDetails)
        }
        binding.btnUpdateProfileDetails.setOnClickListener {
            val oldProfileDetails = getOldProfileDetails()
            val newProfileDetails = getNewProfileDetails()
            updateProfileDetails(oldProfileDetails, newProfileDetails)
        }
    }

    private fun getOldProfileDetails(): ProfileDetails {
        val oldFirstName = binding.etOldFirstName.text.toString() // GET Old FirstName
        val oldLastName = binding.etOldLastName.text.toString() // GET Old LastName
        val oldAge = binding.etOldAge.text.toString().toInt() // GET Old Age
        return ProfileDetails(oldFirstName, oldLastName, oldAge)
    }

    private fun saveProfileDetails(profileDetails: ProfileDetails) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                profileDetailsCollectionRef.add(profileDetails)
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@FireStoreApp, "Data Saved Successfully", Toast.LENGTH_LONG)
                        .show()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@FireStoreApp, e.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun readProfileDetailsData() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val querySnapshot = profileDetailsCollectionRef.get().await() // get data
                val stringBuilder = StringBuilder()
                for (document in querySnapshot.documents) {
                    val profileDetails = document.toObject<ProfileDetails>()
                    stringBuilder.append("$profileDetails\n")
                }
                withContext(Dispatchers.Main) {
                    binding.tvReadProfileDetails.text = stringBuilder.toString()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@FireStoreApp, e.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun deleteProfileDetails(profileDetails: ProfileDetails) {
        CoroutineScope(Dispatchers.IO).launch {
            val profileDetailsQuery = profileDetailsCollectionRef
                .whereEqualTo("firstName", profileDetails.firstName)
                .whereEqualTo("lastName", profileDetails.lastName)
                .whereEqualTo("age", profileDetails.age)
                .get()
                .await()

            if (profileDetailsQuery.documents.isNotEmpty()) {
                for (document in profileDetailsQuery) {
                    try {
                        profileDetailsCollectionRef.document(document.id).delete().await()
                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                this@FireStoreApp,
                                "Data Deleted Successfully",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    } catch (e: Exception) {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(this@FireStoreApp, e.message, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            } else {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@FireStoreApp,
                        "No Profile Details Matched in Query",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun getNewProfileDetails(): Map<String, Any> {
        val newFirstname = binding.etNewFirstName.text.toString()
        val newLastname = binding.etNewLastName.text.toString()
        val newAge = binding.etNewAge.text.toString()
        val map = mutableMapOf<String, Any>()
        if (newFirstname.isNotEmpty()) {
            map["firstName"] = newFirstname
        }
        if (newLastname.isNotEmpty()) {
            map["lastName"] = newLastname
        }
        if (newAge.isNotEmpty()) {
            map["age"] = newAge.toInt()
        }
        return map;
    }

    private fun updateProfileDetails(
        oldProfileDetails: ProfileDetails,
        newProfileDetails: Map<String, Any>
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            val profileDetailsQuery = profileDetailsCollectionRef
                .whereEqualTo("firstName", oldProfileDetails.firstName)
                .whereEqualTo("lastName", oldProfileDetails.lastName)
                .whereEqualTo("age", oldProfileDetails.age)
                .get()
                .await()

            if (profileDetailsQuery.documents.isNotEmpty()) {
                for (document in profileDetailsQuery) {
                    try {
                        profileDetailsCollectionRef.document(document.id).set(
                            newProfileDetails,
                            SetOptions.merge()
                        ).await()
                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                this@FireStoreApp,
                                "Data Updated Successfully",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    } catch (e: Exception) {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(this@FireStoreApp, e.message, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            } else {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@FireStoreApp,
                        "No Profile Details Matched in Query",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

    }

    private fun subscribeToRealTimeUpdates() {
        profileDetailsCollectionRef.addSnapshotListener { querySnapShot, firebaseFireStoreException ->
            firebaseFireStoreException?.let {
                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
            }
            querySnapShot?.let {it ->
                val stringBuilder = StringBuilder()
                for (document in it){
                     val profileDetails = document.toObject<ProfileDetails>()
                    stringBuilder.append("$profileDetails\n")
                }
                binding.tvReadProfileDetails.text = stringBuilder.toString()
            }

        }
    }

}