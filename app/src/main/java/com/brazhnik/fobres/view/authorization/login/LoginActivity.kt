package com.brazhnik.fobres.view.authorization.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.brazhnik.fobres.data.SharedData
import com.brazhnik.fobres.data.database.room.creator.FobresDatabase
import com.brazhnik.fobres.data.model.ProfileFull
import com.brazhnik.fobres.databinding.ActivityLoginBinding
import com.brazhnik.fobres.utilities.displayToast
import com.brazhnik.fobres.view.authorization.register.RegisterActivity
import com.brazhnik.fobres.view.main.MainActivity
import com.brazhnik.fobres.view.profile.editprofile.EditPresenter
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginView {

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
            checkLoginSuccess()
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

        SharedData.profileFullCurrent = ProfileFull(
            "1",
            "login1",
            "Dmitry",
            "Brazhnik",
            "I'm sail very delicious honey",
            "Sail very delicious honey",
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

    override fun checkLoginSuccess() {

        if (binding.etLoginUser.text.toString() != "" || binding.etLoginPassword.text.toString() != "") {

            loginPresenter.loginHelper.checkAuth(
                binding.etLoginUser.text.toString(),
                binding.etLoginPassword.text.toString()
            )
            loginPresenter.response.observe(this) {
                this.displayToast("Успешно авторизован!")
                // Отправить запрос, получить пользователя по id
                // записать в шаред дата
                SharedData._userToken = "Bearer " + it.token
                // Получаем профиль по id
                loginPresenter.profileHelper.getCurrentProfileAPI(1)
            }
            loginPresenter.profileFull.observe(this) {
                this.displayToast("Профиль получен")
                SharedData.profileFullCurrent = it

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                SharedData.isLogged = true
                finish()
            }
        } else {
            this.displayToast("Error: Поля не заполнены")
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