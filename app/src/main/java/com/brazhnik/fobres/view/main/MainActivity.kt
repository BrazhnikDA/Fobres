package com.brazhnik.fobres.view.main

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.brazhnik.fobres.R
import com.brazhnik.fobres.repository.data.TypeRating
import com.brazhnik.fobres.view.profile.ProfileActivity
import com.brazhnik.fobres.view.rating.RatingActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnProfile: Button = findViewById(R.id.button)
        val btnRating: Button = findViewById(R.id.button2)

        btnProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
        btnRating.setOnClickListener {
            startActivity(Intent(this, RatingActivity::class.java))
        }
    }
}