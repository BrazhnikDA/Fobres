package com.brazhnik.fobres.view.authorization.login

import androidx.lifecycle.MutableLiveData
import com.arellomobile.mvp.MvpPresenter
import com.brazhnik.fobres.data.LoginUseCase
import com.brazhnik.fobres.data.helper.ModelLoginHelper
import com.brazhnik.fobres.data.helper.ModelProfileHelper
import com.brazhnik.fobres.data.helper.ModelTokenHelper
import com.brazhnik.fobres.data.model.AuthResponse
import com.brazhnik.fobres.data.model.ProfileFull
import com.brazhnik.fobres.data.model.Token
import com.brazhnik.fobres.view.profile.editprofile.EditView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginPresenter : MvpPresenter<LoginView>(), LoginUseCase {

    val profileFull: MutableLiveData<ProfileFull> = MutableLiveData()
    val response: MutableLiveData<AuthResponse> = MutableLiveData()
    val status: MutableLiveData<String> = MutableLiveData()

    val profileHelper: ModelProfileHelper = ModelProfileHelper(profileFull, status)
    val loginHelper: ModelLoginHelper = ModelLoginHelper(response, status)
    private val tokenHelper: ModelTokenHelper = ModelTokenHelper(null)

    override fun checkAuth(login: String, password: String) {
        loginHelper.checkAuth(login, password)
    }

    fun saveToken(token: Token) {
        CoroutineScope(Dispatchers.IO).launch {
            tokenHelper.setToken(token)
        }
    }
}