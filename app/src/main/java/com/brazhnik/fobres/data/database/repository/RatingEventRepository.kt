package com.brazhnik.fobres.data.database.repository

import android.content.Context
import com.brazhnik.fobres.data.model.Rating

interface RatingEventRepository {
    suspend fun saveListRating(context: Context, listRating: List<Rating>)

    suspend fun getRatingAll(context: Context): List<Rating>
    suspend fun getRatingCountry(context: Context, country: String): List<Rating>
    suspend fun getRatingCity(context: Context, city: String): List<Rating>

}