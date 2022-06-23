package com.brazhnik.fobres.data.helper

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.brazhnik.fobres.data.model.Profile
import com.brazhnik.fobres.data.network.service.ServiceProfile
import com.brazhnik.fobres.view.profile.ProfileView

class ModelProfileHelper (
    context: Context,
    private val profile: MutableLiveData<Profile>
    ) : ProfileView {

    private val serviceProfile: ServiceProfile = ServiceProfile()

    override fun getCurrentProfileAPI(id: Int) {
        serviceProfile.getCurrentProfile(profile, id)
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

    override fun getCurrentProfileDB(id: Int) {
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