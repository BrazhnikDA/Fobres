package com.brazhnik.fobres.data.database.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.brazhnik.fobres.data.database.room.entity.RatingEventEntity

@Dao
interface RatingEventDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveListRating(listRating: RatingEventEntity)

    @Query("SELECT * FROM rating_list_entity")
    fun getListRatingAll(): List<RatingEventEntity>
}