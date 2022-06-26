package com.brazhnik.fobres.data.database.repository

import android.content.Context
import com.brazhnik.fobres.data.model.LogEvent

interface LogEventRepository {
    suspend fun saveLog(log: LogEvent)
}