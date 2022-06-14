package com.brazhnik.fobres.data.storage

import android.content.Context
import androidx.room.Room
import com.brazhnik.fobres.data.database.room.creator.LogDatabase
import com.brazhnik.fobres.data.database.room.dao.LogEventDao
import com.brazhnik.fobres.data.database.room.entity.LogEventEntity
import com.brazhnik.fobres.data.database.room.repository.RoomLogEventRepository
import com.brazhnik.fobres.data.model.Rating
import java.sql.Time

class StorageRating(context: Context) {
    private var logDao: LogEventDao

    init {
        val db = Room.databaseBuilder(
            context,
            LogDatabase::class.java, "log_database"
        ).build()
        logDao = db.logDao()
    }

    // This fun testing and after few weeks i need added implementation for request to server and get response
    fun getAllUsersDB(countItem: Int): List<Rating> {
        logDao.saveLogEvent(
            LogEventEntity(
                null,
                "Storage",
                System.currentTimeMillis(),
                "GetAllUsersDB",
                null,
                null
            )
        )
        val tmpList: ArrayList<Rating> = ArrayList()
        if (countItem > -1) {
            for (i in 0..50) {
                tmpList.add(
                    Rating(
                        id = i.toString(),
                        login = "1",
                        firstName = "1",
                        lastName = "2",
                        profileDescription = "3213",
                        profilePicture = "321",
                        country = "323",
                        city = "33333",
                        money = "123123123"
                    )
                )
            }
        } else {
            for (i in 0..countItem) {
                /*tmpList.add(
                    VModelRating(
                        name = "Dmitry Brazhnik",
                        amount = (10..3567).random().toString(),
                        placeRating = (i + 1).toString()
                    )
                )*/

            }
        }
        return tmpList.toList()
    }
}