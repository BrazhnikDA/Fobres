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

        private var ratingDatabase: FobresDatabase? = null

        private fun initializeDB(context: Context): FobresDatabase {
            return FobresDatabase.getDatabaseFobres(context)
        }

        override suspend fun saveListRating(context: Context, listRating: List<Rating>) {
            if (ratingDatabase == null)
                ratingDatabase = initializeDB(context)

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

        override suspend fun getListRating(context: Context): List<Rating> {
            if (ratingDatabase == null)
                ratingDatabase = initializeDB(context)
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
                return result
            } catch (ex: Exception) {
                Log.d("DB: ", "Table for rating is empty!")
                return arrayListOf()
            }
        }
    }
}
