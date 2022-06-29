package com.brazhnik.fobres.view.rating

import androidx.lifecycle.MutableLiveData
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.brazhnik.fobres.data.helper.ModelProfileHelper
import com.brazhnik.fobres.data.helper.ModelRatingHelper
import com.brazhnik.fobres.data.model.ShortUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

@InjectViewState
class RatingPresenter: MvpPresenter<RatingView>() {

    private val modelRatingHelper: ModelRatingHelper

    val listUser: MutableLiveData<List<ShortUser>> = MutableLiveData()
    val statusResponse: MutableLiveData<String> = MutableLiveData()

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

    suspend fun setRatingAllDB(listShortUser: List<ShortUser>) {
        modelRatingHelper.setRatingAllDB(listShortUser)
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
}