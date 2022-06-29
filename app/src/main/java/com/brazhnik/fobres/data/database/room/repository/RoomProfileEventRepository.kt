package com.brazhnik.fobres.data.database.room.repository

import android.content.Context
import com.brazhnik.fobres.data.SharedData
import com.brazhnik.fobres.data.database.repository.ProfileEventRepository
import com.brazhnik.fobres.data.database.room.creator.FobresDatabase
import com.brazhnik.fobres.data.database.room.entity.ProfileEventEntity
import com.brazhnik.fobres.data.model.ProfileFull

class RoomProfileEventRepository {
    companion object : ProfileEventRepository {

        var profileDatabase: FobresDatabase? = null

        fun initializeDB(context: Context): FobresDatabase {
            return FobresDatabase.getDatabaseFobres(context)
        }

        override suspend fun saveProfile(profileFull: ProfileFull) {
            profileDatabase!!.profileDao().saveProfile(
                ProfileEventEntity(
                    id = profileFull.id.toLong(),
                    login = profileFull.login,
                    firstName = profileFull.firstName,
                    lastName = profileFull.lastName,
                    profileDescription = profileFull.profileDescription,
                    status = profileFull.status,
                    profilePicture = profileFull.profilePicture,
                    country = profileFull.country,
                    city = profileFull.city,
                    money = profileFull.money,
                    worldPlace = profileFull.worldPlace,
                    countryPlace = profileFull.countryPlace,
                    cityPlace = profileFull.cityPlace
                )
            )
        }

        override suspend fun getProfile(): ProfileFull {
            val profile = profileDatabase!!.profileDao().getProfile()
            return if (profile.isNotEmpty()) {
                ProfileFull(
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
                    SharedData.profileFullCurrent.worldPlace,
                    SharedData.profileFullCurrent.countryPlace,
                    SharedData.profileFullCurrent.cityPlace
                )
            }else {
                ProfileFull((-1).toString(), "_", "_", "_", "_", "_", "_", "_","_", "_", "_", "_", "_")
            }
        }
    }
}