package com.brazhnik.fobres.view.rating

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.brazhnik.fobres.R
import com.brazhnik.fobres.data.helper.ModelRatingHelper
import com.brazhnik.fobres.data.model.Rating
import com.brazhnik.fobres.data.model.TypeRating
import com.brazhnik.fobres.data.network.service.ServiceRating
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@InjectViewState
class RatingPresenter constructor(context: Context) : MvpPresenter<RatingView>() {

    private val modelRatingHelper: ModelRatingHelper

    var listUser: MutableLiveData<List<Rating>> = MutableLiveData()
    var statusResponse: MutableLiveData<String> = MutableLiveData()

    init {
        modelRatingHelper = ModelRatingHelper(context, listUser, statusResponse)
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

    suspend fun getRatingAllDB(context: Context) {
        modelRatingHelper.getRatingAllDB(context)
    }

    suspend fun getRatingCountryDB(context: Context, country: String) {
        modelRatingHelper.getRatingCountryDB(context, country)
    }

    suspend fun getRatingCityDB(context: Context, city: String) {
        modelRatingHelper.getRatingCityDB(context, city)
    }
}