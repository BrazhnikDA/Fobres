package com.brazhnik.fobres.repository.models

import com.brazhnik.fobres.repository.storage.StorageProfile
import com.brazhnik.fobres.repository.data.Profile
import com.brazhnik.fobres.view.profile.ProfileView

class ModelProfileHelper (private val storageProfile: StorageProfile) : ProfileView {
    override fun getProfileInfo(): Profile {
        return storageProfile.getProfileInfo()
    }

    override fun openHistoryDeposit() {
        TODO("Not yet implemented")
    }

    override fun viewHowGuest() {
        TODO("Not yet implemented")
    }

    override fun changeProfile() {
        TODO("Not yet implemented")
    }

    override fun getCurrentProfile(): Profile {
        return storageProfile.getCurrentProfile()
    }
}