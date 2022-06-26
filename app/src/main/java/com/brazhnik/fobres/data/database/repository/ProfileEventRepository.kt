package com.brazhnik.fobres.data.database.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.brazhnik.fobres.data.model.Profile

interface ProfileEventRepository {
    suspend fun saveProfile(profile: Profile, world: String, country: String, city: String)

    suspend fun getProfile(): Profile

    suspend fun getCountryProfile(selectionTypeCountry: MutableLiveData<String>)
    suspend fun getCityProfile(selectionTypeCity: MutableLiveData<String>)
}