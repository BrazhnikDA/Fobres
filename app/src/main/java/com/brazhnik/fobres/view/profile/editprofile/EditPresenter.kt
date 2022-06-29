package com.brazhnik.fobres.view.profile.editprofile

import androidx.lifecycle.MutableLiveData
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.brazhnik.fobres.data.SharedData
import com.brazhnik.fobres.data.helper.ModelProfileHelper
import com.brazhnik.fobres.data.model.ProfileFull

@InjectViewState
class EditPresenter: MvpPresenter<EditView>() {

    val profileFull: MutableLiveData<ProfileFull> = MutableLiveData()
    val status: MutableLiveData<String> = MutableLiveData()

    private val modelProfileHelper: ModelProfileHelper = ModelProfileHelper(profileFull, status)

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        fillFields()
    }

    private fun fillFields() {
        viewState.fillFields(SharedData.profileFullCurrent)
    }

    fun updateProfile(profileFull: ProfileFull) {
        modelProfileHelper.updateCurrentProfileAPI(profileFull)
    }

    fun getProfile(): ProfileFull {
        return SharedData.profileFullCurrent
    }

    fun updateLocalProfile(it: ProfileFull?) {
        if (it != null) {
            SharedData.profileFullCurrent = it
        }
    }
}