package com.brazhnik.fobres.data.model

class LogEvent(
    val logPlace: String,
    val logTime: Long,
    val logText: String,
    val logBody: String?,
    val logError: String?
)