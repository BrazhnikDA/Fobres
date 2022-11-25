package com.brazhnik.fobres.view.authorization.register

import android.content.Intent
import android.os.Bundle
import android.view.View
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
import com.brazhnik.fobres.data.model.Token
import com.brazhnik.fobres.databinding.ActivityRegisterBinding
import com.brazhnik.fobres.utilities.Validator
import com.brazhnik.fobres.utilities.displayToast
import com.brazhnik.fobres.view.authorization.login.LoginPresenter
import com.brazhnik.fobres.view.main.MainActivity

class RegisterActivity : AppCompatActivity(), RegisterView {

    private val validator = Validator()
    private var listError: HashMap<Int, String> = hashMapOf()

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
            val login = binding.etLogin.text.toString()
            val password = binding.etPassword.text.toString()
            val confirmPassword = binding.etPasswordConfirm.text.toString()
            val firstName = binding.etFirstName.text.toString()
            val lastName = binding.etLastName.text.toString()

            listError.clear()
            listError.putAll(validator.isValidLogin(login))
            listError.putAll(validator.isValidPassword(password))
            listError.putAll(validator.isValidConfirmPassword(password, confirmPassword))
            listError.putAll(validator.isValidName(firstName))
            listError.putAll(validator.isValidName(lastName))
            if(listError.size == 0) {
                binding.textViewListError.visibility = View.GONE
                val user = RegistrationUser(
                    login = binding.etLogin.text.toString(),
                    firstName = binding.etFirstName.text.toString(),
                    lastName = binding.etLastName.text.toString(),
                    country = binding.etCountry.text.toString(),
                    city = binding.etCity.text.toString(),
                    password = binding.etPassword.text.toString()
                )

                registerPresenter.registrationUser(user)
            } else {
                binding.textViewListError.visibility = View.VISIBLE
                binding.textViewListError.text = validator.textForShowScreen(listError)
            }
        }

        registerPresenter.response.observe(this) {
            this.displayToast("Быстрее проверь свой рейтинг!")
            SharedData._userToken = "Bearer " + it.token
            registerPresenter.saveToken(Token(it.username, it.token))
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