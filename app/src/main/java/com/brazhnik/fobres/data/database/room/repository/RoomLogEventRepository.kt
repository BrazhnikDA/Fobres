package com.brazhnik.fobres.data.database.room.repository

import android.content.Context
import androidx.room.Room
import com.brazhnik.fobres.data.database.room.creator.LogDatabase
import com.brazhnik.fobres.data.database.repository.LogEventRepository
import com.brazhnik.fobres.data.database.room.dao.LogEventDao
import com.brazhnik.fobres.data.database.room.entity.LogEventEntity
import com.brazhnik.fobres.data.model.LogEvent

class RoomLogEventRepository(
    private val logEventDao: LogEventDao
) : LogEventRepository {

    override suspend fun saveLog(log: LogEvent) {
        // here we need call fun delete oldest logs for example >10 days
        logEventDao.saveLogEvent(
            LogEventEntity(
                logId = null,
                logPlace = log.logPlace,
                logTimeCreate = log.logTime,
                logText = log.logText,
                logBody = log.logBody,
                logError = log.logError
            )
        )
    }
}