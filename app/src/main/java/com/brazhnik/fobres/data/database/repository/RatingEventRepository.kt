package com.brazhnik.fobres.data.database.repository

import com.brazhnik.fobres.data.model.ShortUser

interface RatingEventRepository {
    suspend fun saveListRating(listShortUser: List<ShortUser>)

    suspend fun getRatingAll(): List<ShortUser>
    suspend fun getRatingCountry(country: String): List<ShortUser>
    suspend fun getRatingCity(city: String): List<ShortUser>

}