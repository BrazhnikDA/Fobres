package com.brazhnik.fobres.data.database.repository

import com.brazhnik.fobres.data.model.LogEvent

interface LogEventRepository {
    suspend fun saveLog(log: LogEvent)
}