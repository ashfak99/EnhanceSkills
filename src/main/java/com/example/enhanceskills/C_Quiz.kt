package com.example.enhanceskills

import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.enhanceskills.databinding.ActivityCquizBinding
import com.google.android.material.card.MaterialCardView


class C_Quiz : AppCompatActivity() {
    private lateinit var binding: ActivityCquizBinding
    private lateinit var card1: MaterialCardView
    private lateinit var card2:MaterialCardView
    private lateinit var card3:MaterialCardView
    private lateinit var card4:MaterialCardView
    private lateinit var card5:MaterialCardView
    private lateinit var card6:MaterialCardView
    private lateinit var card7:MaterialCardView
    private lateinit var card8:MaterialCardView
    private lateinit var card9:MaterialCardView
    private lateinit var card10:MaterialCardView
    private var currentCardIndex=0
    private var currentRadioIndex=0
    private val answer= mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityCquizBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val radiogroup1=binding.RG1
        val radiogroup2=binding.RG2
        val radiogroup3=binding.RG3
        val radiogroup4=binding.RG4
        val radiogroup5=binding.RG5
        val radiogroup6=binding.RG6
        val radiogroup7=binding.RG7
        val radiogroup8=binding.RG8
        val radiogroup9=binding.RG9
        val radiogroup10=binding.RG10

        card1=binding.card1
        card2=binding.card2
        card3=binding.card3
        card4=binding.card4
        card5=binding.card5
        card6=binding.card6
        card7=binding.card7
        card8=binding.card8
        card9=binding.card9
        card10=binding.card10

        val radio= listOf(radiogroup1,radiogroup2,radiogroup3,radiogroup4,radiogroup5,radiogroup6,radiogroup7,radiogroup8,radiogroup9,radiogroup10)
        val cards= listOf(card1,card2,card3,card4,card5,card6,card7,card8,card9,card10)

        binding.NEXT.setOnClickListener {
            val select=radio[currentRadioIndex].checkedRadioButtonId

            if (select!=-1){
                answer.add(findViewById<RadioButton>(select).text.toString())

                if (currentCardIndex<cards.size-1) {
                    cards[currentCardIndex].visibility= View.GONE
                    currentCardIndex++
                    currentRadioIndex++
                    cards[currentCardIndex].visibility=View.VISIBLE
                }
            }
            else{
                Toast.makeText(this, "Please Select An Option", Toast.LENGTH_SHORT).show()
            }
        }

    }
}