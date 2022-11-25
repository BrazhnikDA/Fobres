package com.brazhnik.fobres.view.authorization.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.brazhnik.fobres.data.SharedData
import com.brazhnik.fobres.data.database.room.creator.FobresDatabase
import com.brazhnik.fobres.data.model.ProfileFull
import com.brazhnik.fobres.data.model.Token
import com.brazhnik.fobres.databinding.ActivityLoginBinding
import com.brazhnik.fobres.utilities.Validator
import com.brazhnik.fobres.utilities.displayToast
import com.brazhnik.fobres.view.authorization.register.RegisterActivity
import com.brazhnik.fobres.view.main.MainActivity
import com.brazhnik.fobres.view.profile.editprofile.EditPresenter
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginView {

    private val validator = Validator()
    private var listError: HashMap<Int, String> = hashMapOf()

    @InjectPresenter
    lateinit var loginPresenter: LoginPresenter

    @ProvidePresenter
    fun providePresenter(): LoginPresenter {
        return LoginPresenter()
    }

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loginPresenter = providePresenter()

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val login = binding.etLoginUser.text.toString()
            val pass = binding.etLoginPassword.text.toString()

            listError.clear()
            listError.putAll(validator.isValidLogin(login))
            listError.putAll(validator.isValidPassword(pass))
            if(listError.size == 0) {
                binding.textViewListError.visibility = View.GONE
                checkLoginSuccess()
            } else {
                binding.textViewListError.visibility = View.VISIBLE
                binding.textViewListError.text = validator.textForShowScreen(listError)
            }
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
    }

    override fun checkLoginSuccess() {

        if (binding.etLoginUser.text.toString() != "" || binding.etLoginPassword.text.toString() != "") {

            loginPresenter.loginHelper.checkAuth(
                binding.etLoginUser.text.toString(),
                binding.etLoginPassword.text.toString()
            )
            loginPresenter.response.observe(this) {
                this.displayToast("Погнали!")
                // Отправить запрос, получить пользователя по id
                // записать в шаред дата
                SharedData._userToken = "Bearer " + it.token
                loginPresenter.saveToken(Token(it.username, it.token))
                // Получаем профиль по id
                loginPresenter.profileHelper.getCurrentProfileAPI(it.username)
            }
            loginPresenter.profileFull.observe(this) {
                //this.displayToast("Профиль получен")
                SharedData.profileFullCurrent = it

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                SharedData.isLogged = true
                finish()
            }
        }

        loginPresenter.status.observe(this) {
            this.displayToast(it)
        }
    }

    override fun showError() {
        TODO("Not yet implemented")
    }

    override fun showLoadingWheel() {
        TODO("Not yet implemented")
    }

    override fun hideLoadingWheel() {
        TODO("Not yet implemented")
    }
}