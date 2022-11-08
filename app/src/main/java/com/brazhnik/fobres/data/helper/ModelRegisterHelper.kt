package com.brazhnik.fobres.data.helper

import androidx.lifecycle.MutableLiveData
import com.brazhnik.fobres.data.model.AuthResponse
import com.brazhnik.fobres.data.model.RegistrationUser
import com.brazhnik.fobres.data.network.service.ServiceRegistration

class ModelRegisterHelper(
    val response: MutableLiveData<AuthResponse>,
    val status: MutableLiveData<String>
) {

    private val registerService = ServiceRegistration()

    fun registrationUser(user: RegistrationUser) {
        registerService.registrationUser(response, user, status)
    }
}