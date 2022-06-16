package com.brazhnik.fobres.data.database.room.creator

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.brazhnik.fobres.data.database.room.dao.LogEventDao
import com.brazhnik.fobres.data.database.room.entity.LogEventEntity


@Database(entities = [LogEventEntity::class], version = 1)
abstract class LogDatabase : RoomDatabase() {
    abstract fun logDao(): LogEventDao

    companion object {
        private var INSTANCE: LogDatabase? = null

        fun getInstance(context: Context): LogDatabase? {
            if (INSTANCE == null) {
                synchronized(LogDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        LogDatabase::class.java,
                        "log.db"
                    ).build()
                }
                Room.databaseBuilder(context, LogDatabase::class.java,"DB").allowMainThreadQueries().build()
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
