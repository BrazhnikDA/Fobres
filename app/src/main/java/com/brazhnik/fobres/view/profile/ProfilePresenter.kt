package com.brazhnik.fobres.view.profile

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.brazhnik.fobres.data.helper.ModelProfileHelper
import com.brazhnik.fobres.data.model.Profile
import com.brazhnik.fobres.data.network.service.ServiceProfile

@InjectViewState
class ProfilePresenter (context: Context) : MvpPresenter<ProfileView>() {

    val profile = MutableLiveData<Profile>()
    private var modelProfileHelper: ModelProfileHelper =
        ModelProfileHelper(context, profile)


    fun getCurrentProfileAPI(id: Int) {
        modelProfileHelper.getCurrentProfileAPI(id)
    }

    fun getHistoryDepositAPI(id: Int) {
        TODO("Not yet implemented")
    }

    fun getViewHowGuestAPI(id: Int) {
        TODO("Not yet implemented")
    }

    fun updateProfileAPI(id: Int) {
        TODO("Not yet implemented")
    }

    fun getCurrentProfileDB(id: Int) {
        modelProfileHelper.getCurrentProfileDB(id)
    }

    fun getHistoryDepositDB(id: Int) {
        TODO("Not yet implemented")
    }

    fun getViewHowGuestDB(id: Int) {
        TODO("Not yet implemented")
    }

    fun updateProfileDB(id: Int) {
        TODO("Not yet implemented")
    }

}