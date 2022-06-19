package com.brazhnik.fobres.view.authorization.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.brazhnik.fobres.R
import com.brazhnik.fobres.databinding.ActivityRegisterBinding

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    class RegisterActivity : AppCompatActivity() {
        private lateinit var binding: ActivityRegisterBinding

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            binding = ActivityRegisterBinding.inflate(layoutInflater)
            setContentView(binding.root)

        }
    }
}