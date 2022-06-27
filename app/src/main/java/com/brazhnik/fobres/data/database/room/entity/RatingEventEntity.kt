package com.brazhnik.fobres.data.database.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rating_list_entity")
data class RatingEventEntity(

    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Long,

    @ColumnInfo(name = "money")
    val money: String,

    @ColumnInfo(name = "firstname")
    val firstName: String,

    @ColumnInfo(name = "lastname")
    val lastName: String,

    @ColumnInfo(name = "picture_url")
    val profilePicture: String,

    @ColumnInfo(name = "status")
    val status: String
)