package com.brazhnik.fobres.view.profile

import androidx.lifecycle.MutableLiveData
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.brazhnik.fobres.data.helper.ModelProfileHelper
import com.brazhnik.fobres.data.storage.StorageProfile
import com.brazhnik.fobres.data.model.Profile
import com.brazhnik.fobres.data.network.service.ServiceProfile

@InjectViewState
class ProfilePresenter : MvpPresenter<ProfileView>(), ProfileView {

    private val profile = MutableLiveData<Profile>()
    private var modelProfileHelper: ModelProfileHelper =
        ModelProfileHelper(StorageProfile(), ServiceProfile(), profile)


    override fun getCurrentProfileAPI(id: Int): MutableLiveData<Profile> {
        return modelProfileHelper.getCurrentProfileAPI(id)
    }

    override fun getHistoryDepositAPI(id: Int) {
        TODO("Not yet implemented")
    }

    override fun getViewHowGuestAPI(id: Int) {
        TODO("Not yet implemented")
    }

    override fun updateProfileAPI(id: Int) {
        TODO("Not yet implemented")
    }

    override fun getCurrentProfileDB(id: Int): Profile {
        return modelProfileHelper.getCurrentProfileDB(id)
    }

    override fun getHistoryDepositDB(id: Int) {
        TODO("Not yet implemented")
    }

    override fun getViewHowGuestDB(id: Int) {
        TODO("Not yet implemented")
    }

    override fun updateProfileDB(id: Int) {
        TODO("Not yet implemented")
    }

}