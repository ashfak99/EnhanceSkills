package com.example.enhanceskills

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize Firebase Auth and Database
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().getReference("Learner Type")

        // Check if the user is authenticated
        val currentUser = auth.currentUser
        if (currentUser != null) {
            // User is authenticated, check learner type
            checkUserType(currentUser.uid)
        } else {
            // If user not found, go to authentication page
            goToAuthenticationPage()
        }
    }

    // Function to check the learner type from the database
    private fun checkUserType(userId: String) {
        database.child(userId).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val learnerType = snapshot.getValue(String::class.java)
                    if (learnerType == "Visual") {
                        goToVideoPage()
                    } else {
                        goToReadWritePage()
                    }
                } else {
                    goToAuthenticationPage()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("FirebaseError", "Error fetching learner type: ${error.message}")
            }
        })
    }

    private fun goToVideoPage() {
        val intent = Intent(this, HOME_PAGE::class.java)
        startActivity(intent)
        finish()
    }

    private fun goToReadWritePage() {
        val intent = Intent(this, HOME_PAGE_READ_WRITE::class.java)
        startActivity(intent)
        finish()
    }

    private fun goToAuthenticationPage() {
        val intent = Intent(this, NumberVerification::class.java)
        startActivity(intent)
        finish()
    }
}