package com.brazhnik.fobres.data.database.room.creator

import androidx.room.Database
import androidx.room.RoomDatabase
import com.brazhnik.fobres.data.database.room.dao.LogEventDao
import com.brazhnik.fobres.data.database.room.entity.LogEventEntity

@Database(entities = [LogEventEntity::class], version = 1)
abstract class LogDatabase : RoomDatabase() {
    abstract fun logDao(): LogEventDao
}
