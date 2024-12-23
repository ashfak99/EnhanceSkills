package com.example.enhanceskills

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.OptIn
import androidx.appcompat.app.AppCompatActivity
import androidx.media3.common.util.UnstableApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.enhanceskills.databinding.ActivityCcourseVideoBinding
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class C_course_video : AppCompatActivity() {
    private lateinit var binding: ActivityCcourseVideoBinding
    private lateinit var firebaseRecyclerAdapter: FirebaseRecyclerAdapter<Member, ViewHolder>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCcourseVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up RecyclerView
        binding.recyclerviewVideo.setHasFixedSize(true)
        binding.recyclerviewVideo.layoutManager = LinearLayoutManager(this)

        // Initialize Firebase
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val reference: DatabaseReference = database.getReference("microprocessor")

        // Configure FirebaseRecyclerOptions
        val options = FirebaseRecyclerOptions.Builder<Member>()
            .setQuery(reference, Member::class.java)
            .build()

        // Set up FirebaseRecyclerAdapter
        firebaseRecyclerAdapter = object : FirebaseRecyclerAdapter<Member, ViewHolder>(options) {
            @OptIn(UnstableApi::class)
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.image_video, parent, false)
                return ViewHolder(view)
            }

            @OptIn(UnstableApi::class)
            override fun onBindViewHolder(holder: ViewHolder, position: Int, model: Member) {
                // Use the updated method names from the Member class
                holder.setVideo(application, model.getTitleOrDefault(), model.getUrlOrDefault())
            }
        }

        // Set the adapter
        binding.recyclerviewVideo.adapter = firebaseRecyclerAdapter
    }

    override fun onStart() {
        super.onStart()
        firebaseRecyclerAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        firebaseRecyclerAdapter.stopListening()
    }
}
