package com.brazhnik.fobres.view.authorization.register

import androidx.lifecycle.MutableLiveData
import com.arellomobile.mvp.MvpPresenter
import com.brazhnik.fobres.data.helper.ModelProfileHelper
import com.brazhnik.fobres.data.helper.ModelRegisterHelper
import com.brazhnik.fobres.data.helper.ModelTokenHelper
import com.brazhnik.fobres.data.model.AuthResponse
import com.brazhnik.fobres.data.model.ProfileFull
import com.brazhnik.fobres.data.model.RegistrationUser
import com.brazhnik.fobres.data.model.Token
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.log

class RegisterPresenter: MvpPresenter<RegisterView>() {
    val profile: MutableLiveData<ProfileFull> = MutableLiveData()
    val response: MutableLiveData<AuthResponse> = MutableLiveData()
    val status: MutableLiveData<String> = MutableLiveData()

    private val helperRegister = ModelRegisterHelper(response, status)
    private val helperProfile = ModelProfileHelper(profile,status)
    private val helperToken = ModelTokenHelper(null)

    fun registrationUser(user: RegistrationUser) {
        helperRegister.registrationUser(user)
    }

    fun findUserByLogin(login: String) {
        helperProfile.getCurrentProfileAPI(login)
    }

    fun saveToken(token: Token) {
        CoroutineScope(Dispatchers.IO).launch {
            helperToken.setToken(token)
        }
    }
}