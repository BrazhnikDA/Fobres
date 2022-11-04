package com.brazhnik.fobres.data.database.repository

import com.brazhnik.fobres.data.model.ProfileFull

interface ProfileEventRepository {
    suspend fun saveProfile(profileFull: ProfileFull)

    suspend fun getProfile(): ProfileFull
}