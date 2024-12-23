package com.example.enhanceskills

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.enhanceskills.databinding.ActivityLearnerSurveyBinding
import com.google.android.material.card.MaterialCardView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.selects.select

class Learner_Survey : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var btn: Button
    private lateinit var card1: MaterialCardView
    private lateinit var card2: MaterialCardView
    private lateinit var card3: MaterialCardView
    private lateinit var card4: MaterialCardView
    private lateinit var card5: MaterialCardView
    private var currentCardIndex = 0
    private var currentRadioIndex = 0
    private lateinit var binding: ActivityLearnerSurveyBinding
    private val answers = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLearnerSurveyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()  // Initialize FirebaseAuth

        btn = binding.NEXT

        val radiogroup1 = binding.radioGroup1
        val radiogroup2 = binding.radioGroup2
        val radiogroup3 = binding.radioGroup3
        val radiogroup4 = binding.radioGroup4
        val radiogroup5 = binding.radioGroup5

        card1 = binding.card1
        card2 = binding.card2
        card3 = binding.card3
        card4 = binding.card4
        card5 = binding.card5

        val radioGroupList = listOf(radiogroup1, radiogroup2, radiogroup3, radiogroup4, radiogroup5)
        val cardList = listOf(card1, card2, card3, card4, card5)

        // Hide all cards except the first one
        for (i in 1 until cardList.size) {
            cardList[i].visibility = View.GONE
        }

        btn.setOnClickListener {
            val selected = radioGroupList[currentRadioIndex].checkedRadioButtonId

            if (selected != -1) {
                answers.add(findViewById<RadioButton>(selected).text.toString())

                if (currentCardIndex < cardList.size - 1) {
                    // Hide the current card
                    cardList[currentCardIndex].visibility = View.GONE

                    // Show the next card
                    currentCardIndex++
                    currentRadioIndex++
                    cardList[currentCardIndex].visibility = View.VISIBLE
                } else {
                    val learnertype = findLearnerType(answers)
                    Toast.makeText(this, "You are $learnertype type learner", Toast.LENGTH_SHORT).show()

                    saveEmail()

                    // Navigate to the appropriate homepage
                    if (learnertype == "Visual Learner") {
                        startActivity(Intent(this, HOME_PAGE::class.java))
                    } else {
                        startActivity(Intent(this, HOME_PAGE_READ_WRITE::class.java))
                    }
                    finish()
                }
            } else {
                Toast.makeText(this, "Please select an option", Toast.LENGTH_SHORT).show()
            }
        }
    }


    fun findLearnerType(answers: List<String>): String {
        var video = 0
        var read = 0
        for (answer in answers) {
            when (answer) {
                binding.radioButton1A.text.toString(),
                binding.radioButton2A.text.toString(),
                binding.radioButton3A.text.toString(),
                binding.radioButton4A.text.toString(),
                binding.radioButton5A.text.toString() -> video++

                binding.radioButton1B.text.toString(),
                binding.radioButton2B.text.toString(),
                binding.radioButton3B.text.toString(),
                binding.radioButton4B.text.toString(),
                binding.radioButton5B.text.toString() -> read++
            }
        }
        return if (video > read) {
            "Visual Learner"
        } else {
            "Read/Write Learner"
        }
    }

    private fun saveEmail() {

        val currentUser = auth.currentUser
        if (currentUser != null) {
            val userId = currentUser.uid
            val learnertype = findLearnerType(answers)

            // Check if learner type is not empty before saving to Firebase
            if (learnertype.isNotEmpty()) {
                val databaseReference = FirebaseDatabase.getInstance().getReference("Learner Type")
                    .child(userId)
                    .child("Learner Type")

                // Save the learner type to Firebase
                databaseReference.setValue(learnertype).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Learner Type saved successfully", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Failed to save Learner Type", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Learner Type is empty", Toast.LENGTH_SHORT).show()
            }
        } else {
            // Handle case where the user is not signed in
            Toast.makeText(this, "User is not authenticated", Toast.LENGTH_SHORT).show()
        }
    }
}
