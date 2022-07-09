package com.brazhnik.fobres.view.profile.editprofile

import androidx.lifecycle.MutableLiveData
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.brazhnik.fobres.data.SharedData
import com.brazhnik.fobres.data.helper.ModelProfileHelper
import com.brazhnik.fobres.data.model.Profile
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
        viewState.showLoadingWheel()
        viewState.fillFields(SharedData.profileFullCurrent)
        viewState.hideLoadingWheel()
    }

    fun updateProfile(profileFull: ProfileFull) {
        viewState.showLoadingWheel()
        modelProfileHelper.updateCurrentProfileAPI(profileFull)
        viewState.hideLoadingWheel()
    }

    fun getProfile(): ProfileFull {
        return SharedData.profileFullCurrent
    }

    fun updateLocalProfile(it: ProfileFull) {
        SharedData.profileFullCurrent = it
    }
}