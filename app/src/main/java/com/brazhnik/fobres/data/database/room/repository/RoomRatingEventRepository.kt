package com.brazhnik.fobres.data.database.room.repository

import android.content.Context
import android.util.Log
import com.brazhnik.fobres.data.database.repository.RatingEventRepository
import com.brazhnik.fobres.data.database.room.creator.FobresDatabase
import com.brazhnik.fobres.data.database.room.entity.RatingEventEntity
import com.brazhnik.fobres.data.model.ShortUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class RoomRatingEventRepository {
    companion object : RatingEventRepository {

        var ratingDatabase: FobresDatabase? = null

        fun initializeDB(context: Context): FobresDatabase {
            return FobresDatabase.getDatabaseFobres(context)
        }

        override suspend fun saveListRating(listShortUser: List<ShortUser>) {
            CoroutineScope(IO).launch {
                for (item in listShortUser) {
                    ratingDatabase!!.ratingDao().saveListRating(
                        RatingEventEntity(
                            id = item.id.toLong(),
                            money = item.money,
                            firstName = item.firstName,
                            lastName = item.lastName,
                            profilePicture = item.profilePicture.toString(),
                            status = item.status.toString()
                        )
                    )
                }
            }
        }

        override suspend fun getRatingAll(): List<ShortUser> {
            try {
                val result: ArrayList<ShortUser> = arrayListOf()
                for (item in ratingDatabase!!.ratingDao().getListRatingAll()) {
                    result.add(
                        ShortUser(
                            item.id.toString(),
                            item.money,
                            item.firstName,
                            item.lastName,
                            item.profilePicture,
                            item.status
                        )
                    )
                }
                return if (result.size > 0) result else mutableListOf(
                    ShortUser(
                        "0",
                        "0",
                        "_",
                        "_",
                        "_",
                        "Your history is empty :("
                    )
                )
            } catch (ex: Exception) {
                Log.d("DB: ", "Table for rating is empty!")
                return arrayListOf()
            }
        }

        override suspend fun getRatingCountry(country: String): List<ShortUser> {
            try {
                val result: ArrayList<ShortUser> = arrayListOf()
                for (item in ratingDatabase!!.ratingDao().getListRatingCountry()) {
                    result.add(
                        ShortUser(
                            id = item.id.toString(),
                            money = item.money,
                            firstName = item.firstName,
                            lastName = item.lastName,
                            profilePicture = item.profilePicture.toString(),
                            status = item.status
                        )
                    )
                }
                return if (result.size > 0) result else mutableListOf(
                    ShortUser(
                        "0",
                        "0",
                        "_",
                        "_",
                        "_",
                        "Your history is empty :("
                    )
                )
            } catch (ex: Exception) {
                Log.d("DB: ", "Table for rating is empty!")
                return arrayListOf()
            }
        }

        override suspend fun getRatingCity(city: String): List<ShortUser> {
            try {
                val result: ArrayList<ShortUser> = arrayListOf()
                for (item in ratingDatabase!!.ratingDao().getListRatingCity()) {
                    result.add(
                        ShortUser(
                            item.id.toString(),
                            item.money,
                            item.firstName,
                            item.lastName,
                            item.profilePicture,
                            item.status
                        )
                    )
                }
                return if (result.size > 0) result else mutableListOf(
                    ShortUser(
                        "0",
                        "0",
                        "_",
                        "_",
                        "_",
                        "Your history is empty :("
                    )
                )
            } catch (ex: Exception) {
                Log.d("DB: ", "Table for rating is empty!")
                return arrayListOf()
            }
        }
    }
}
