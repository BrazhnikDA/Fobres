package com.brazhnik.fobres.view.rating

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.arellomobile.mvp.MvpView
import com.brazhnik.fobres.repository.data.Rating

/**
 * If fun contains in name "API" - this fun getting data from server else
 * data getting in local DB
 */

interface RatingView : MvpView {
    fun getListUserRatingAllAPI() : MutableLiveData<List<Rating>>
    fun getListUserRatingCityAPI(city: String) : MutableLiveData<List<Rating>>
    fun getListUserRatingCountryAPI(country: String) : MutableLiveData<List<Rating>>

    fun getListUserRatingAllDB(countItem : Int) : List<Rating>
}