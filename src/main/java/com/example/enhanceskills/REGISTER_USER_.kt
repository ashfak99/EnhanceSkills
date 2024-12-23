package com.example.enhanceskills

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import com.google.android.gms.common.util.UidVerifier
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import java.text.DateFormat
import java.util.Calendar

class REGISTER_USER_ : AppCompatActivity() {

    private var imageURL: String? = null
    private var uri: Uri? = null
    private lateinit var auth: FirebaseAuth
    private lateinit var data:DatabaseReference
    private lateinit var img: ImageView
    private lateinit var btn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_user2)
        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()
        val uid=auth.currentUser!!.uid
        data=FirebaseDatabase.getInstance().reference
        val userdatav=data.child("Testing").child(uid)
        data.child("Testing").child(uid!!).addListenerForSingleValueEvent(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    val intent=Intent(this@REGISTER_USER_,HOME_PAGE::class.java)
                    startActivity(intent)
                    finish()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Firebase","Failed to read Value. ")
            }
        })

        // Check if user is already logged in
        img = findViewById(R.id.profilepic)
        btn = findViewById(R.id.NEXT)

        val activityResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data
                uri = data?.data
                img.setImageURI(uri)
            } else {
                Toast.makeText(this@REGISTER_USER_, "No Image Selected", Toast.LENGTH_SHORT).show()
            }
        }

        img.setOnClickListener {
            val photoPicker = Intent(Intent.ACTION_PICK)
            photoPicker.type = "image/*"
            activityResultLauncher.launch(photoPicker)
        }

        btn.setOnClickListener {
            if (uri != null) {
                saveData()
            } else {
                Toast.makeText(this@REGISTER_USER_, "Please select an image", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun saveData() {
        val storageReference = FirebaseStorage.getInstance().reference.child("Task Images")
            .child(uri?.lastPathSegment ?: "Unknown")

        val builder = AlertDialog.Builder(this@REGISTER_USER_)
        builder.setCancelable(false)
        builder.setView(R.layout.progress_layout)
        val dialog = builder.create()
        dialog.show()

        storageReference.putFile(uri!!).addOnSuccessListener { taskSnapshot ->
            val uriTask = taskSnapshot.storage.downloadUrl
            uriTask.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val urlImage = task.result
                    imageURL = urlImage.toString()
                    uploadData()  // Upload data after image upload is complete
                }
                dialog.dismiss()
            }
        }.addOnFailureListener {
            dialog.dismiss()
            Toast.makeText(this@REGISTER_USER_, "Image Upload Failed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun uploadData() {
        val name = findViewById<TextInputEditText>(R.id.name).text.toString()
        val age = findViewById<TextInputEditText>(R.id.age).text.toString()
        val city = findViewById<TextInputEditText>(R.id.city).text.toString()
        val uid=auth.currentUser!!.uid
        val dataClass = UserModel(name, age, city, imageURL.toString())
        val currentDate = DateFormat.getDateTimeInstance().format(Calendar.getInstance().time)

        val databaseReference = FirebaseDatabase.getInstance().getReference("Testing").child(uid)
        databaseReference.setValue(dataClass).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(this@REGISTER_USER_, "Saved", Toast.LENGTH_SHORT).show()
                val intent = Intent(this,Learner_Survey::class.java)
                startActivity(intent)
                finish()
            }
        }.addOnFailureListener {
            Toast.makeText(this@REGISTER_USER_, "FAILED", Toast.LENGTH_SHORT).show()
        }
    }
}
