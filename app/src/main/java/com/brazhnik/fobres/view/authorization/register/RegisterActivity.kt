package com.brazhnik.fobres.view.authorization.register

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.brazhnik.fobres.data.SharedData
import com.brazhnik.fobres.data.model.RegistrationUser
import com.brazhnik.fobres.databinding.ActivityRegisterBinding
import com.brazhnik.fobres.utilities.displayToast
import com.brazhnik.fobres.view.authorization.login.LoginPresenter
import com.brazhnik.fobres.view.main.MainActivity

class RegisterActivity : AppCompatActivity(), RegisterView {
    private lateinit var binding: ActivityRegisterBinding

    @InjectPresenter
    lateinit var registerPresenter: RegisterPresenter

    @ProvidePresenter
    fun providePresenter(): RegisterPresenter {
        return RegisterPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        registerPresenter = providePresenter()

        val buttonRegister = binding.buttonRegister

        buttonRegister.setOnClickListener {
            val user = RegistrationUser(
                login = binding.etLogin.text.toString(),
                firstName = binding.etFirstName.text.toString(),
                lastName = binding.etLastName.text.toString(),
                country = binding.etCountry.text.toString(),
                city = binding.etCity.text.toString(),
                password = binding.etPassword.text.toString()
            )

            registerPresenter.registrationUser(user)
        }

        registerPresenter.response.observe(this) {
            this.displayToast("УСПЕХ" + it.token)
            SharedData._userToken = "Bearer " + it.token
            registerPresenter.findUserByLogin(it.username)
        }
        registerPresenter.profile.observe(this) {
            SharedData.profileFullCurrent = it
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            SharedData.isLogged = true
            finish()
        }
    }
}