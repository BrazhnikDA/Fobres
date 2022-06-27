package com.brazhnik.fobres.data.database.room.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.brazhnik.fobres.data.SharedData
import com.brazhnik.fobres.data.database.repository.ProfileEventRepository
import com.brazhnik.fobres.data.database.room.creator.FobresDatabase
import com.brazhnik.fobres.data.database.room.entity.ProfileEventEntity
import com.brazhnik.fobres.data.model.Profile
import kotlinx.coroutines.*

class RoomProfileEventRepository {
    companion object : ProfileEventRepository {

        var profileDatabase: FobresDatabase? = null

        fun initializeDB(context: Context): FobresDatabase {
            return FobresDatabase.getDatabaseFobres(context)
        }

        override suspend fun saveProfile(
            profile: Profile,
            world: String,
            country: String,
            city: String
        ) {
            profileDatabase!!.profileDao().saveProfile(
                ProfileEventEntity(
                    id = profile.id.toLong(),
                    login = profile.login,
                    firstName = profile.firstName,
                    lastName = profile.lastName,
                    profileDescription = profile.profileDescription,
                    status = profile.status,
                    profilePicture = profile.profilePicture,
                    country = profile.country,
                    city = profile.city,
                    money = profile.money,
                    worldPlace = world,
                    countryPlace = country,
                    cityPlace = city
                )
            )
        }

        override suspend fun getProfile(): Profile {
            val profile = profileDatabase!!.profileDao().getProfile()
            return if (profile.isNotEmpty()) {
                Profile(
                    id = profile[0].id.toString(),
                    login = profile[0].login,
                    firstName = profile[0].firstName,
                    lastName = profile[0].lastName,
                    profileDescription = profile[0].profileDescription,
                    status = profile[0].status,
                    profilePicture = profile[0].profilePicture,
                    country = profile[0].country,
                    city = profile[0].city,
                    money = profile[0].money,
                    SharedData.profileCurrent.worldPlace,
                    SharedData.profileCurrent.countryPlace,
                    SharedData.profileCurrent.cityPlace
                )
            }else {
                Profile((-1).toString(), "_", "_", "_", "_", "_", "_", "_","_", "_", "_", "_", "_")
            }
        }

        override suspend fun getCountryProfile(selectionTypeCountry: MutableLiveData<String>) {
            selectionTypeCountry.postValue(profileDatabase!!.profileDao().getCountry())
        }

        override suspend fun getCityProfile(selectionTypeCity: MutableLiveData<String>) {
            val a = profileDatabase!!.profileDao().getCity()
            print(a)
            //selectionTypeCity.postValue(profileDatabase!!.profileDao().getCity())
        }
    }
}