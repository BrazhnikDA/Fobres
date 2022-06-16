package com.brazhnik.fobres.view.rating

import android.content.Context
import com.arellomobile.mvp.MvpView
import com.brazhnik.fobres.data.model.Rating

/**
 * If fun contains in name "API" - this fun getting data from server else
 * data getting in local DB
 */

interface RatingView : MvpView {
    //region API Call
    suspend fun getRatingAllAPI()
    suspend fun getRatingCityAPI(city: String)
    suspend fun getRatingCountryAPI(country: String)
    //endregion

    //region Room DB Call
    suspend fun setRatingAllDB(listRating: List<Rating>)
    suspend fun getRatingAllDB(context: Context)
    //endregion
}