package com.example.enhanceskills

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.enhanceskills.databinding.ActivityHomePageBinding

class HOME_PAGE : AppCompatActivity() {
    private lateinit var binding:ActivityHomePageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.profile.setOnClickListener{
            val Intent=Intent(this,User_Profile::class.java)
            startActivity(Intent)
        }

        val textview2=binding.logotext
        val text= listOf("Let's Enhance Your Coding Skills ")
        val textAnimation=text_animation(textview2,text)
        textAnimation.start()

        val textview=binding.scrolltext
        val texts= listOf("Ashfak Alam","Imteyaz Ali","Azimul Huda","Faiyaz Ahmad","Farhin Ashraf")
        val textAnimator=text_animation(textview,texts)
        textAnimator.start()

        binding.course.setOnClickListener{
         binding.card1.visibility=View.GONE
            binding.scrolling.visibility=View.GONE
            binding.coursequiz.visibility=View.GONE
            binding.coursevideo.visibility=View.VISIBLE
        }
        binding.home.setOnClickListener{
            binding.card1.visibility=View.VISIBLE
            binding.scrolling.visibility=View.VISIBLE
            binding.coursequiz.visibility=View.GONE
            binding.coursevideo.visibility=View.GONE
        }
        binding.quiz.setOnClickListener{
            binding.card1.visibility=View.GONE
            binding.scrolling.visibility=View.GONE
            binding.coursequiz.visibility=View.VISIBLE
            binding.coursevideo.visibility=View.GONE
        }

        binding.MicroProcessor.setOnClickListener{
            val Intent=Intent(this,C_course_video::class.java)
            startActivity(Intent)
        }

        binding.cquiz.setOnClickListener {
            val Intent = Intent(this, C_Quiz::class.java)
            startActivity(Intent)
        }
    }
}