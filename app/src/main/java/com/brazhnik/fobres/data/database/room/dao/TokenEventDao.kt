package com.brazhnik.fobres.data.database.room.dao

import androidx.room.*
import com.brazhnik.fobres.data.database.room.entity.LogEventEntity
import com.brazhnik.fobres.data.database.room.entity.TokenEventEntity
import com.brazhnik.fobres.data.model.Token

@Dao
interface TokenEventDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveToken(token: TokenEventEntity)

    @Query("SELECT * FROM token_entity")
    fun getToken(): TokenEventEntity

    @Query("DELETE FROM token_entity")
    fun deleteToken()
}