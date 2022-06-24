package com.brazhnik.fobres.data.database.room.creator

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.brazhnik.fobres.data.database.room.dao.LogEventDao
import com.brazhnik.fobres.data.database.room.dao.ProfileEventDao
import com.brazhnik.fobres.data.database.room.dao.RatingEventDao
import com.brazhnik.fobres.data.database.room.entity.LogEventEntity
import com.brazhnik.fobres.data.database.room.entity.ProfileEventEntity
import com.brazhnik.fobres.data.database.room.entity.RatingEventEntity

@Database(entities = [LogEventEntity::class, RatingEventEntity::class, ProfileEventEntity::class], version = 7)
abstract class FobresDatabase : RoomDatabase() {
    abstract fun logDao(): LogEventDao
    abstract fun ratingDao(): RatingEventDao
    abstract fun profileDao(): ProfileEventDao

    companion object {
        @Volatile
        private var INSTANCE: FobresDatabase? = null

        fun getDatabaseFobres(context: Context): FobresDatabase {
            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {
                INSTANCE = Room
                    .databaseBuilder(context, FobresDatabase::class.java, "fobres_db")
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCE!!
            }
        }

        fun destroyDataBase(){
            INSTANCE = null
        }
    }
}
