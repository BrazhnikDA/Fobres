package com.brazhnik.fobres.data.helper

import androidx.lifecycle.MutableLiveData
import com.brazhnik.fobres.data.LoginUseCase
import com.brazhnik.fobres.data.model.AuthRequest
import com.brazhnik.fobres.data.model.AuthResponse
import com.brazhnik.fobres.data.network.service.ServiceLogin
import com.brazhnik.fobres.data.network.service.ServiceRating
import kotlin.math.log

class ModelLoginHelper(
    val response: MutableLiveData<AuthResponse>,
    val status: MutableLiveData<String>
) : LoginUseCase {

    private val loginService: ServiceLogin = ServiceLogin()

    override fun checkAuth(login: String, password: String) {
        loginService.checkLoginSuccess(response, AuthRequest(login, password), status)
    }
}