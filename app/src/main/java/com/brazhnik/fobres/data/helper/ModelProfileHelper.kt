package com.brazhnik.fobres.data.helper

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.brazhnik.fobres.data.database.room.repository.RoomProfileEventRepository
import com.brazhnik.fobres.data.model.Profile
import com.brazhnik.fobres.data.network.service.ServiceProfile
import com.brazhnik.fobres.view.profile.ProfileView

class ModelProfileHelper (
    private val profile: MutableLiveData<Profile>,
    private val status: MutableLiveData<String>
    ) : ProfileView {

    private val serviceProfile: ServiceProfile = ServiceProfile()

    fun getCurrentProfileAPI(id: Int) {
        serviceProfile.getCurrentProfile(profile, id, status)
    }

    suspend fun setProfileDB(profile: Profile) {
        // Get on api world country city place of rating
        RoomProfileEventRepository.saveProfile(profile, "55", "33", "27")
    }

    suspend fun getProfileDB() {
        profile.postValue(RoomProfileEventRepository.getProfile())
    }

    companion object {
        suspend fun getCountry(selectionTypeCountry: MutableLiveData<String>) {
            RoomProfileEventRepository.getCountryProfile(selectionTypeCountry)
        }

        suspend fun getCity(selectionTypeCity: MutableLiveData<String>) {
            RoomProfileEventRepository.getCityProfile(selectionTypeCity)
        }
    }
}