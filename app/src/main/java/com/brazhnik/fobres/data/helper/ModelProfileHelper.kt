package com.brazhnik.fobres.data.helper

import androidx.lifecycle.MutableLiveData
import com.brazhnik.fobres.data.database.room.repository.RoomProfileEventRepository
import com.brazhnik.fobres.data.model.Profile
import com.brazhnik.fobres.data.model.ProfileFull
import com.brazhnik.fobres.data.model.UpdateImageAnswer
import com.brazhnik.fobres.data.network.service.ServiceProfile
import com.brazhnik.fobres.view.profile.ProfileView

class ModelProfileHelper (
    private val profileFull: MutableLiveData<ProfileFull>,
    private val status: MutableLiveData<String>
    ) : ProfileView {

    private val serviceProfile: ServiceProfile = ServiceProfile()

    fun updateCurrentProfileAPI(profileFull: ProfileFull) {
        serviceProfile.updateCurrentProfile(this.profileFull, profileFull, status)
    }

    fun getCurrentProfileAPI(id: Int) {
        serviceProfile.getCurrentProfile(profileFull, id, status)
    }

    fun uploadImageToServer(pathToImage: String, id: Int, responseImageUrl: MutableLiveData<UpdateImageAnswer>) {
        serviceProfile.uploadImage(responseImageUrl, pathToImage, id, status)
    }

    suspend fun setProfileDB(profileFull: ProfileFull) {
        // Get on api world country city place of rating
        RoomProfileEventRepository.saveProfile(profileFull)
    }

    suspend fun getProfileDB() {
        profileFull.postValue(RoomProfileEventRepository.getProfile())
    }
}