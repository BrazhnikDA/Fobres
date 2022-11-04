package com.brazhnik.fobres.data.database.room.repository

import android.content.Context
import com.brazhnik.fobres.data.database.repository.LogEventRepository
import com.brazhnik.fobres.data.database.room.creator.FobresDatabase
import com.brazhnik.fobres.data.database.room.entity.LogEventEntity
import com.brazhnik.fobres.data.model.LogEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class RoomLogEventRepository {

    companion object : LogEventRepository {

        var logDatabase: FobresDatabase? = null

        fun initializeDB(context: Context): FobresDatabase {
            return FobresDatabase.getDatabaseFobres(context)
        }

        override suspend fun saveLog(log: LogEvent) {
            CoroutineScope(IO).launch {
                logDatabase!!.logDao().saveLogEvent(
                    LogEventEntity(
                        logPlace = log.logPlace,
                        logTimeCreate = log.logTime,
                        logText = log.logText,
                        logBody = log.logBody,
                        logError = log.logError
                    )
                )
            }

        }
    }
}