package com.brazhnik.fobres.data.database.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "current_user_entity")
class CurrentUserEntity (
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Long,

    @ColumnInfo(name = "login")
    val login: String,

    @ColumnInfo(name = "firstname")
    val firstName: String,

    @ColumnInfo(name = "lastname")
    val lastName: String,

    @ColumnInfo(name = "profile_description")
    val profileDescription: String,

    @ColumnInfo(name = "picture_url")
    val profilePicture: String,

    @ColumnInfo(name = "country")
    val country: String,

    @ColumnInfo(name = "city")
    val city: String,

    @ColumnInfo(name = "money")
    val money: String,

    @ColumnInfo(name = "password")
    val password: String,

    @ColumnInfo(name = "date_registry")
    val date: Date,

    @ColumnInfo(name = "last_session")
    val dateSession: Date
)