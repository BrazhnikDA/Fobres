package com.brazhnik.fobres.view.profile

import androidx.lifecycle.MutableLiveData
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.brazhnik.fobres.data.helper.ModelProfileHelper
import com.brazhnik.fobres.data.helper.ModelTokenHelper
import com.brazhnik.fobres.data.model.ProfileFull
import com.brazhnik.fobres.data.model.Token
import kotlinx.coroutines.*

@InjectViewState
class ProfilePresenter : MvpPresenter<ProfileView>() {

    val profileFull: MutableLiveData<ProfileFull> = MutableLiveData()
    val status: MutableLiveData<String> = MutableLiveData()

    private val scope = CoroutineScope(Dispatchers.IO)

    private var modelProfileHelper: ModelProfileHelper =
        ModelProfileHelper(profileFull, status)

    private var token: MutableLiveData<Token> = MutableLiveData<Token>()
    private val tokenHelper: ModelTokenHelper = ModelTokenHelper(token)

    fun getCurrentProfileAPI(id: Int) {
        scope.launch {
            //delay(2000) // TODO DELETE
            modelProfileHelper.getCurrentProfileAPI(id)
        }
    }

    fun removeCurrentTokenDB(token: Token) {
        scope.launch {
            tokenHelper.deleteToken(token)
        }
    }

    fun setProfileDB(profileFull: ProfileFull) {
        scope.launch {
            modelProfileHelper.setProfileDB(profileFull)
        }
    }

    fun getProfileDB() {
        scope.launch {
            modelProfileHelper.getProfileDB()
        }
    }
}