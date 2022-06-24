package com.brazhnik.fobres.data.database.repository

import android.content.Context
import com.brazhnik.fobres.data.model.Profile

interface ProfileEventRepository {
    suspend fun saveProfile(
        context: Context,
        profile: Profile,
        world: String,
        country: String,
        city: String
    )

    suspend fun getProfile(): Profile
}