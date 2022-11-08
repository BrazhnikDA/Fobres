package com.brazhnik.fobres.view.authorization.register

import androidx.lifecycle.MutableLiveData
import com.arellomobile.mvp.MvpPresenter
import com.brazhnik.fobres.data.helper.ModelProfileHelper
import com.brazhnik.fobres.data.helper.ModelRegisterHelper
import com.brazhnik.fobres.data.model.AuthResponse
import com.brazhnik.fobres.data.model.ProfileFull
import com.brazhnik.fobres.data.model.RegistrationUser
import kotlin.math.log

class RegisterPresenter: MvpPresenter<RegisterView>() {
    val profile: MutableLiveData<ProfileFull> = MutableLiveData()
    val response: MutableLiveData<AuthResponse> = MutableLiveData()
    val status: MutableLiveData<String> = MutableLiveData()

    private val helperRegister = ModelRegisterHelper(response, status)
    private val helperProfile = ModelProfileHelper(profile,status)


    fun registrationUser(user: RegistrationUser) {
        helperRegister.registrationUser(user)
    }

    fun findUserByLogin(login: String) {
        helperProfile.getCurrentProfileAPI(login)
    }
}