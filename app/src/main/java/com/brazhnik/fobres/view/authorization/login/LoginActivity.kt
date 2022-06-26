package com.brazhnik.fobres.view.authorization.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.brazhnik.fobres.data.SharedData
import com.brazhnik.fobres.data.database.room.creator.FobresDatabase
import com.brazhnik.fobres.data.model.Profile
import com.brazhnik.fobres.databinding.ActivityLoginBinding
import com.brazhnik.fobres.view.authorization.register.RegisterActivity
import com.brazhnik.fobres.view.main.MainActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            SharedData.isLogged = true
            finish()
        }

        binding.textViewRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            SharedData.isLogged = false
        }

        binding.btnGuest.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            SharedData.isLogged = false
            finish()
        }

        SharedData.profileCurrent = Profile(
            "8",
            "login1",
            "Dmitry",
            "Brazhnik",
            "I'm sail very delicious honey",
            "picture1.png",
            "Россия",
            "Нижний Новгород",
            "550",
            "10",
            "33",
            "50"
        )

        // Init all DB
        FobresDatabase.initAllTableDB(applicationContext)
    }
}