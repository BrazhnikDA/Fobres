package com.brazhnik.fobres.view.rating

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.brazhnik.fobres.data.helper.ModelProfileHelper
import com.brazhnik.fobres.data.helper.ModelRatingHelper
import com.brazhnik.fobres.data.model.Rating

@InjectViewState
class RatingPresenter: MvpPresenter<RatingView>() {

    private val modelRatingHelper: ModelRatingHelper

    val listUser: MutableLiveData<List<Rating>> = MutableLiveData()
    val statusResponse: MutableLiveData<String> = MutableLiveData()
    val selectionTypeCountry: MutableLiveData<String> = MutableLiveData()
    val selectionTypeCity: MutableLiveData<String> = MutableLiveData()

    init {
        modelRatingHelper = ModelRatingHelper(listUser, statusResponse)
    }

    fun getRatingAllAPI() {
        modelRatingHelper.getRatingAllAPI()
    }

    fun getRatingCityAPI(city: String) {
        modelRatingHelper.getRatingCityAPI(city)
    }

    fun getRatingCountryAPI(country: String) {
        modelRatingHelper.getRatingCountryAPI(country)
    }

    suspend fun setRatingAllDB(listRating: List<Rating>) {
        modelRatingHelper.setRatingAllDB(listRating)
    }

    suspend fun getRatingAllDB() {
        modelRatingHelper.getRatingAllDB()
    }

    suspend fun getRatingCountryDB(country: String) {
        modelRatingHelper.getRatingCountryDB(country)
    }

    suspend fun getRatingCityDB(city: String) {
        modelRatingHelper.getRatingCityDB(city)
    }

    suspend fun getCountryProfile() {
        ModelProfileHelper.getCountry(selectionTypeCountry)
    }

    suspend fun getCityProfile() {
        ModelProfileHelper.getCity(selectionTypeCity)
    }
}