package com.brazhnik.fobres.view.rating

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.arellomobile.mvp.MvpPresenter
import com.brazhnik.fobres.data.model.Rating
import com.brazhnik.fobres.data.helper.ModelRatingHelper
import com.brazhnik.fobres.data.network.service.ServiceRating
import com.brazhnik.fobres.data.storage.StorageRating

class RatingPresenter(context: Context) : MvpPresenter<RatingView>(), RatingView {

    private val listRating = MutableLiveData<List<Rating>>()
    var modelRatingHelper: ModelRatingHelper = ModelRatingHelper(StorageRating(context), ServiceRating(), listRating)


    override fun getListUserRatingAllAPI() : MutableLiveData<List<Rating>> {
        return modelRatingHelper.getListUserRatingAllAPI()
    }

    override fun getListUserRatingCityAPI(city: String): MutableLiveData<List<Rating>> {
        return modelRatingHelper.getListUserRatingCityAPI(city)
    }

    override fun getListUserRatingCountryAPI(country: String): MutableLiveData<List<Rating>> {
        return modelRatingHelper.getListUserRatingCountryAPI(country)
    }

    override fun getListUserRatingAllDB(countItem: Int): List<Rating> {
        return modelRatingHelper.getListUserRatingAllDB(countItem)
    }


}