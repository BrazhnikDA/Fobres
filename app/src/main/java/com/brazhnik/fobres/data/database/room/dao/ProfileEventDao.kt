package com.brazhnik.fobres.data.database.room.dao

import android.provider.ContactsContract
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.brazhnik.fobres.data.database.repository.ProfileEventRepository
import com.brazhnik.fobres.data.database.room.entity.ProfileEventEntity

@Dao
interface ProfileEventDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveProfile(profile: ProfileEventEntity)

    @Query("SELECT * FROM profile_entity")
    fun getProfile(): List<ProfileEventEntity>

    @Query("DELETE FROM profile_entity WHERE id = :id")
    fun logout(id: String)

    @Query("SELECT country FROM profile_entity")
    fun getCountry(): String

    @Query("SELECT city FROM profile_entity")
    fun getCity(): String
}