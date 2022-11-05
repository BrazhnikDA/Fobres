package com.brazhnik.fobres.view.authorization.login

import androidx.lifecycle.MutableLiveData
import com.arellomobile.mvp.MvpPresenter
import com.brazhnik.fobres.data.LoginUseCase
import com.brazhnik.fobres.data.helper.ModelLoginHelper
import com.brazhnik.fobres.data.helper.ModelProfileHelper
import com.brazhnik.fobres.data.model.AuthResponse
import com.brazhnik.fobres.data.model.ProfileFull
import com.brazhnik.fobres.view.profile.editprofile.EditView

class LoginPresenter: MvpPresenter<LoginView>(), LoginUseCase {

    val profileFull:  MutableLiveData<ProfileFull> = MutableLiveData()
    val response: MutableLiveData<AuthResponse> = MutableLiveData()
    val status: MutableLiveData<String> = MutableLiveData()

    val profileHelper: ModelProfileHelper = ModelProfileHelper(profileFull, status)
    val loginHelper: ModelLoginHelper = ModelLoginHelper(response, status)

    override fun checkAuth(login: String, password: String) {
        loginHelper.checkAuth(login, password)
    }


}