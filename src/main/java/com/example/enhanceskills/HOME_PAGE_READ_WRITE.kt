package com.example.enhanceskills

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.enhanceskills.databinding.ActivityHomePageReadWriteBinding

class HOME_PAGE_READ_WRITE : AppCompatActivity() {
    private lateinit var binding: ActivityHomePageReadWriteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHomePageReadWriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.profile.setOnClickListener{
            val Intent= Intent(this,User_Profile_Read_Write::class.java)
            startActivity(Intent)
        }

        val btnC=binding.c

        val textview=binding.scrolltext
        val texts= listOf("Ashfak Alam","Asrar Alam")
        val textAnimator=text_animation(textview,texts)
        textAnimator.start()

        binding.course.setOnClickListener{
            binding.card1.visibility= View.GONE
            binding.scrolling.visibility= View.GONE
            binding.coursequiz.visibility= View.GONE
            binding.coursevideo.visibility= View.VISIBLE
        }
        binding.home.setOnClickListener{
            binding.card1.visibility= View.VISIBLE
            binding.scrolling.visibility= View.VISIBLE
            binding.coursequiz.visibility= View.GONE
            binding.coursevideo.visibility= View.GONE
        }
        binding.quiz.setOnClickListener{
            binding.card1.visibility= View.GONE
            binding.scrolling.visibility= View.GONE
            binding.coursequiz.visibility= View.VISIBLE
            binding.coursevideo.visibility= View.GONE
        }
        btnC.setOnClickListener{
            val Intent=Intent(this,c_Read_write::class.java)
            startActivity(Intent)
        }
    }
}