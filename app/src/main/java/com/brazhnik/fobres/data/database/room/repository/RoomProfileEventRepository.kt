package com.brazhnik.fobres.data.database.room.repository

import android.content.Context
import com.brazhnik.fobres.data.database.repository.ProfileEventRepository
import com.brazhnik.fobres.data.database.room.creator.FobresDatabase
import com.brazhnik.fobres.data.database.room.entity.ProfileEventEntity
import com.brazhnik.fobres.data.model.Profile

class RoomProfileEventRepository {
    companion object : ProfileEventRepository {

        private var profileDatabase: FobresDatabase? = null

        private fun initializeDB(context: Context): FobresDatabase {
            return FobresDatabase.getDatabaseFobres(context)
        }

        override suspend fun saveProfile(
            context: Context,
            profile: Profile,
            world: String,
            country: String,
            city: String
        ) {
            if (profileDatabase == null)
                profileDatabase = initializeDB(context)

            profileDatabase!!.profileDao().saveProfile(
                ProfileEventEntity(
                    id = profile.id.toLong(),
                    login = profile.login,
                    firstName = profile.firstName,
                    lastName = profile.lastName,
                    profileDescription = profile.profileDescription,
                    profilePicture = profile.profilePicture.toString(),
                    country = profile.country,
                    city = profile.city,
                    money = profile.money,
                    worldPlace = world,
                    countryPlace = country,
                    cityPlace = city
                )
            )
        }
    }
}