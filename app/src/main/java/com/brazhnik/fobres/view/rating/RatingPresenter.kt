package com.brazhnik.fobres.view.rating

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.arellomobile.mvp.MvpPresenter
import com.brazhnik.fobres.data.helper.ModelRatingHelper
import com.brazhnik.fobres.data.model.Rating
import com.brazhnik.fobres.data.network.service.ServiceRating

class RatingPresenter(
    viewLifecycleOwner: LifecycleOwner,
    context: Context,
    listRating: MutableLiveData<List<Rating>>,
    statusResponse: MutableLiveData<String>
) : MvpPresenter<RatingView>(), RatingView {

    private var modelRatingHelper: ModelRatingHelper = ModelRatingHelper(
        viewLifecycleOwner,
        context,
        ServiceRating(),
        listRating,
        statusResponse
    )

    override suspend fun getRatingAllAPI() {
        modelRatingHelper.getRatingAllAPI()
    }

    override suspend fun getRatingCityAPI(city: String) {
        modelRatingHelper.getRatingCityAPI(city)
    }

    override suspend fun getRatingCountryAPI(country: String) {
        modelRatingHelper.getRatingCountryAPI(country)
    }

    override suspend fun setRatingAllDB(listRating: List<Rating>) {
        modelRatingHelper.setRatingAllDB(listRating)
    }

    override suspend fun getRatingAllDB(context: Context) {
        modelRatingHelper.getRatingAllDB(context)
    }


}