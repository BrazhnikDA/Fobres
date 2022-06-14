package com.brazhnik.fobres.data.database.room.dao

import androidx.room.Dao
import androidx.room.Insert
import com.brazhnik.fobres.data.database.room.entity.LogEventEntity

@Dao
interface LogEventDao {
    @Insert
    fun saveLogEvent(log: LogEventEntity)
}