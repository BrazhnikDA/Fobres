package com.brazhnik.fobres.data.helper

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.brazhnik.fobres.data.database.room.repository.RoomProfileEventRepository
import com.brazhnik.fobres.data.model.Profile
import com.brazhnik.fobres.data.network.service.ServiceProfile
import com.brazhnik.fobres.view.profile.ProfileView

class ModelProfileHelper (
    private val context: Context,
    private val profile: MutableLiveData<Profile>,
    private val status: MutableLiveData<String>
    ) : ProfileView {

    private val serviceProfile: ServiceProfile = ServiceProfile()

    override fun getCurrentProfileAPI(id: Int) {
        serviceProfile.getCurrentProfile(profile, id, status)
    }

    suspend fun setProfileDB(profile: Profile) {
        // Get on api world country city place of rating
        RoomProfileEventRepository.saveProfile(context, profile, "55", "33", "27")
    }

    suspend fun getProfileDB() {
        profile.postValue(RoomProfileEventRepository.getProfile())
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