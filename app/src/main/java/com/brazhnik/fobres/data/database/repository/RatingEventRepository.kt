package com.brazhnik.fobres.data.database.repository

import android.content.Context
import com.brazhnik.fobres.data.model.Rating

interface RatingEventRepository {
    suspend fun saveListRating(context: Context, listRating: List<Rating>)
    suspend fun getListRating(context: Context): List<Rating>
}