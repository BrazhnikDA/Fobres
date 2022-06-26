package com.brazhnik.fobres.data.database.repository

import android.content.Context
import com.brazhnik.fobres.data.model.Rating

interface RatingEventRepository {
    suspend fun saveListRating(listRating: List<Rating>)

    suspend fun getRatingAll(): List<Rating>
    suspend fun getRatingCountry(country: String): List<Rating>
    suspend fun getRatingCity(city: String): List<Rating>

}