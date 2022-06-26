package com.brazhnik.fobres.data.database.room.repository

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.util.Log
import com.brazhnik.fobres.data.database.repository.RatingEventRepository
import com.brazhnik.fobres.data.database.room.creator.FobresDatabase
import com.brazhnik.fobres.data.database.room.entity.RatingEventEntity
import com.brazhnik.fobres.data.model.Rating
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class RoomRatingEventRepository {
    companion object : RatingEventRepository {

        var ratingDatabase: FobresDatabase? = null

        fun initializeDB(context: Context): FobresDatabase {
            return FobresDatabase.getDatabaseFobres(context)
        }

        override suspend fun saveListRating(listRating: List<Rating>) {
            CoroutineScope(IO).launch {
                for (item in listRating) {
                    ratingDatabase!!.ratingDao().saveListRating(
                        RatingEventEntity(
                            id = item.id.toLong(),
                            login = item.login,
                            firstName = item.firstName,
                            lastName = item.lastName,
                            profileDescription = item.profileDescription,
                            profilePicture = item.profilePicture.toString(),
                            country = item.country,
                            city = item.city,
                            money = item.money
                        )
                    )
                }
            }
        }

        override suspend fun getRatingAll(): List<Rating> {
            try {
                val result: ArrayList<Rating> = arrayListOf()
                for (item in ratingDatabase!!.ratingDao().getListRatingAll()) {
                    result.add(
                        Rating(
                            item.id.toString(),
                            item.login,
                            item.firstName,
                            item.lastName,
                            item.profileDescription,
                            item.profilePicture,    // FIX this need image
                            item.country,
                            item.city,
                            item.money
                        )
                    )
                }
                return if (result.size > 0) result else mutableListOf(
                    Rating(
                        "0",
                        "_",
                        "Your history is empty :(",
                        "_",
                        "_",
                        "_",
                        "_",
                        "_",
                        "0"
                    )
                )
            } catch (ex: Exception) {
                Log.d("DB: ", "Table for rating is empty!")
                return arrayListOf()
            }
        }

        override suspend fun getRatingCountry(country: String): List<Rating> {
            try {
                val result: ArrayList<Rating> = arrayListOf()
                for (item in ratingDatabase!!.ratingDao().getListRatingCountry(country)) {
                    result.add(
                        Rating(
                            item.id.toString(),
                            item.login,
                            item.firstName,
                            item.lastName,
                            item.profileDescription,
                            item.profilePicture,    // FIX this need image
                            item.country,
                            item.city,
                            item.money
                        )
                    )
                }
                return if (result.size > 0) result else mutableListOf(
                    Rating(
                        "0",
                        "_",
                        "Your history is empty :(",
                        "_",
                        "_",
                        "_",
                        "_",
                        "_",
                        "0"
                    )
                )
            } catch (ex: Exception) {
                Log.d("DB: ", "Table for rating is empty!")
                return arrayListOf()
            }
        }

        override suspend fun getRatingCity(city: String): List<Rating> {
            try {
                val result: ArrayList<Rating> = arrayListOf()
                for (item in ratingDatabase!!.ratingDao().getListRatingCity(city)) {
                    result.add(
                        Rating(
                            item.id.toString(),
                            item.login,
                            item.firstName,
                            item.lastName,
                            item.profileDescription,
                            item.profilePicture,    // FIX this need image
                            item.country,
                            item.city,
                            item.money
                        )
                    )
                }
                return if (result.size > 0) result else mutableListOf(
                    Rating(
                        "0",
                        "_",
                        "Your history is empty :(",
                        "_",
                        "_",
                        "_",
                        "_",
                        "_",
                        "0"
                    )
                )
            } catch (ex: Exception) {
                Log.d("DB: ", "Table for rating is empty!")
                return arrayListOf()
            }
        }
    }
}
