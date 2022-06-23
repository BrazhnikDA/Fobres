package com.brazhnik.fobres.data.database.room.dao

import android.provider.ContactsContract
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.brazhnik.fobres.data.database.room.entity.ProfileEventEntity

@Dao
interface ProfileEventDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveProfile(profile: ProfileEventEntity)
}