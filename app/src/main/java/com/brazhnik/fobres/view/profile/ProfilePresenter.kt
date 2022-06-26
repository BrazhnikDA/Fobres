package com.brazhnik.fobres.view.profile

import androidx.lifecycle.MutableLiveData
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.brazhnik.fobres.data.helper.ModelProfileHelper
import com.brazhnik.fobres.data.model.Profile
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@InjectViewState
class ProfilePresenter: MvpPresenter<ProfileView>() {

    val profile: MutableLiveData<Profile> = MutableLiveData()
    val status: MutableLiveData<String> = MutableLiveData()

    private var modelProfileHelper: ModelProfileHelper =
        ModelProfileHelper(profile, status)


    fun getCurrentProfileAPI(id: Int) {
        modelProfileHelper.getCurrentProfileAPI(id)
    }

    fun setProfileDB(profile: Profile) {
        GlobalScope.launch {
            modelProfileHelper.setProfileDB(profile)
        }
    }

    fun getProfileDB() {
        GlobalScope.launch {
            modelProfileHelper.getProfileDB()
        }
    }
}