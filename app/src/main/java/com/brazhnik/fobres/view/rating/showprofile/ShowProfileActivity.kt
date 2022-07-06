package com.brazhnik.fobres.view.rating

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.brazhnik.fobres.databinding.ActivityShowProfileBinding

class ShowProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShowProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityShowProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.extras!!["id"].toString()
    }
}