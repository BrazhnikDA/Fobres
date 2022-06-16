package com.brazhnik.fobres.data.database.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rating_list_entity")
data class RatingEventEntity(

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
    val money: String

) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
}