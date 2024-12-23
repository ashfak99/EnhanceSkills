package com.example.enhanceskills

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.enhanceskills.databinding.ActivityCreadWriteBinding
import com.google.android.material.card.MaterialCardView

class c_Read_write : AppCompatActivity() {
    private lateinit var binding:ActivityCreadWriteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityCreadWriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val cardIntro = findViewById<MaterialCardView>(R.id.card_intro)
        val cardVariables = findViewById<MaterialCardView>(R.id.card_variables)
        val cardOperators = findViewById<MaterialCardView>(R.id.card_operators)
        val cardControlFlow = findViewById<MaterialCardView>(R.id.card_controlflow)
        val cardLoops = findViewById<MaterialCardView>(R.id.card_loops)
        val cardFunctions = findViewById<MaterialCardView>(R.id.card_functions)
        val cardArrays = findViewById<MaterialCardView>(R.id.card_arrays)
        val cardPointers = findViewById<MaterialCardView>(R.id.card_pointers)

        // Buttons to navigate through chapters
        val nextIntroButton = findViewById<Button>(R.id.nextIntro)
        val nextVariablesButton = findViewById<Button>(R.id.nextVariables)
        val nextOperatorsButton = findViewById<Button>(R.id.nextOperators)
        val nextControlFlowButton = findViewById<Button>(R.id.nextControlFlow)
        val nextLoopsButton = findViewById<Button>(R.id.nextLoops)
        val nextFunctionsButton = findViewById<Button>(R.id.nextFunctions)
        val nextArraysButton = findViewById<Button>(R.id.nextArrays)

        // Handling visibility changes for each chapter
        nextIntroButton.setOnClickListener {
            cardIntro.visibility = View.GONE
            cardVariables.visibility = View.VISIBLE
        }

        nextVariablesButton.setOnClickListener {
            cardVariables.visibility = View.GONE
            cardOperators.visibility = View.VISIBLE
        }

        nextOperatorsButton.setOnClickListener {
            cardOperators.visibility = View.GONE
            cardControlFlow.visibility = View.VISIBLE
        }

        nextControlFlowButton.setOnClickListener {
            cardControlFlow.visibility = View.GONE
            cardLoops.visibility = View.VISIBLE
        }

        nextLoopsButton.setOnClickListener {
            cardLoops.visibility = View.GONE
            cardFunctions.visibility = View.VISIBLE
        }

        nextFunctionsButton.setOnClickListener {
            cardFunctions.visibility = View.GONE
            cardArrays.visibility = View.VISIBLE
        }

        nextArraysButton.setOnClickListener {
            cardArrays.visibility = View.GONE
            cardPointers.visibility = View.VISIBLE
        }
    }
}