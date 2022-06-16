package com.brazhnik.fobres.data.helper

import androidx.lifecycle.MutableLiveData
import com.brazhnik.fobres.data.model.Profile
import com.brazhnik.fobres.data.network.service.ServiceProfile
import com.brazhnik.fobres.view.profile.ProfileView

class ModelProfileHelper (
    private val serviceProfile: ServiceProfile,
    private val list: MutableLiveData<Profile>
    ) : ProfileView {

    override fun getCurrentProfileAPI(id: Int): MutableLiveData<Profile> {
        return serviceProfile.getCurrentProfile(list, id)
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
        TODO("Not yet implemented")
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