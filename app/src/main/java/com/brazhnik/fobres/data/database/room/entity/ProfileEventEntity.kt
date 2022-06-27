package com.brazhnik.fobres.data.database.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profile_entity")
data class ProfileEventEntity (
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

    @ColumnInfo(name = "status")
    val status: String,

    @ColumnInfo(name = "picture_url")
    val profilePicture: String,

    @ColumnInfo(name = "country")
    val country: String,

    @ColumnInfo(name = "city")
    val city: String,

    @ColumnInfo(name = "money")
    val money: String,

    @ColumnInfo(name = "world_place")
    val worldPlace: String,

    @ColumnInfo(name = "country_place")
    val countryPlace: String,

    @ColumnInfo(name = "city_place")
    val cityPlace: String
)