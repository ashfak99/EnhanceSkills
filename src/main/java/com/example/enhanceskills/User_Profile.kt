package com.example.enhanceskills

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.enhanceskills.databinding.ActivityUserProfileBinding

class User_Profile : AppCompatActivity() {
    private lateinit var binding: ActivityUserProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.logout.setOnClickListener{
            val Intent=Intent(this,NumberVerification::class.java)
            startActivity(Intent)
            finish()
        }
        binding.leftButton.setOnClickListener{
            val Intent=Intent(this,HOME_PAGE::class.java)
            startActivity(Intent)
            finish()
        }
    }
}