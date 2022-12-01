package com.brazhnik.fobres.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.brazhnik.fobres.data.SharedData
import com.brazhnik.fobres.data.SharedData.Companion.YANDEX_MOBILE_ADS_TAG
import com.brazhnik.fobres.data.database.room.creator.FobresDatabase
import com.brazhnik.fobres.data.helper.ModelProfileHelper
import com.brazhnik.fobres.data.helper.ModelTokenHelper
import com.brazhnik.fobres.data.model.AuthResponse
import com.brazhnik.fobres.data.model.ProfileFull
import com.brazhnik.fobres.data.model.Token
import com.brazhnik.fobres.utilities.isOnline
import com.brazhnik.fobres.view.authorization.login.LoginActivity
import com.brazhnik.fobres.view.main.MainActivity
import com.yandex.mobile.ads.common.MobileAds
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private var token: MutableLiveData<Token> = MutableLiveData<Token>()
    val profileFull: MutableLiveData<ProfileFull> = MutableLiveData()
    val status: MutableLiveData<String> = MutableLiveData()

    private val tokenHelper: ModelTokenHelper = ModelTokenHelper(token)
    private val profileHelper: ModelProfileHelper = ModelProfileHelper(profileFull, status)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Init all DB
        FobresDatabase.initAllTableDB(applicationContext)

        if (isOnline(this)) {
            // GET Token
            CoroutineScope(Dispatchers.IO).launch {
                tokenHelper.getToken()
            }

            // Init ADB Yandex
            MobileAds.initialize(this) {
                Log.d(YANDEX_MOBILE_ADS_TAG, "SDK initialized");
            }

            checkTokenFromDatabase()
        } else {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun checkTokenFromDatabase() {
        token.observe(this) {
            if (it.token != "404") {
                SharedData._userToken = "$it"
                profileHelper.getCurrentProfileAPI(it.login)
            } else {
                SharedData.isLogged = false
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        profileFull.observe(this) {
            SharedData.profileFullCurrent = it
            SharedData.isLogged = true

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        status.observe(this) {
            SharedData.isLogged = false
            SharedData._userToken = ""

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}