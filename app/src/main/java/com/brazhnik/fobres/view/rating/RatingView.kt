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
    fun getListUserRatingAPI() : MutableLiveData<List<Rating>>

    fun getListUserRating(countItem : Int) : List<Rating>
}