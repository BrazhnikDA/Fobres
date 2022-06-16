package com.brazhnik.fobres.data.database.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "log_event_entity")
data class LogEventEntity(

    @ColumnInfo(name = "log_place")
    val logPlace: String,

    @ColumnInfo(name = "log_time")
    var logTimeCreate: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "log_text")
    var logText: String,

    @ColumnInfo(name = "log_value")
    var logBody: String?,

    @ColumnInfo(name = "log_error")
    var logError: String?
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var logId: Long? = null
}