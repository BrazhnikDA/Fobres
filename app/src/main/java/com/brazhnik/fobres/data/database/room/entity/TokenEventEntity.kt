package com.brazhnik.fobres.data.database.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "token_entity")
data class TokenEventEntity(
    @PrimaryKey
    @ColumnInfo(name = "user_login")
    val login: String,

    @ColumnInfo(name = "user_token")
    val token: String
)