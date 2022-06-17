package com.brazhnik.fobres.view.rating

import android.content.Context
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.brazhnik.fobres.R
import com.brazhnik.fobres.data.helper.ModelRatingHelper
import com.brazhnik.fobres.data.model.Rating
import com.brazhnik.fobres.data.model.TypeRating
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@InjectViewState
class RatingPresenter constructor(
    private val modelRatingHelper: ModelRatingHelper
) : MvpPresenter<RatingView>() {

    suspend fun getRatingAllAPI() {
        modelRatingHelper.getRatingAllAPI()
    }

    suspend fun getRatingCityAPI(city: String) {
        modelRatingHelper.getRatingCityAPI(city)
    }

    suspend fun getRatingCountryAPI(country: String) {
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