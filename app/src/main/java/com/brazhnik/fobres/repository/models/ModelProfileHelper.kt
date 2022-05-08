package com.brazhnik.fobres.repository.models

import androidx.lifecycle.MutableLiveData
import com.brazhnik.fobres.repository.storage.StorageProfile
import com.brazhnik.fobres.repository.data.Profile
import com.brazhnik.fobres.repository.data.Rating
import com.brazhnik.fobres.repository.network.service.ServiceProfile
import com.brazhnik.fobres.repository.network.service.ServiceRating
import com.brazhnik.fobres.repository.storage.StorageRating
import com.brazhnik.fobres.view.profile.ProfileView

class ModelProfileHelper (
    private val storageProfile: StorageProfile,
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