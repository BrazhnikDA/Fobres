package com.brazhnik.fobres.view.rating

import androidx.lifecycle.MutableLiveData
import com.arellomobile.mvp.MvpPresenter
import com.brazhnik.fobres.repository.data.Rating
import com.brazhnik.fobres.repository.models.ModelRatingHelper
import com.brazhnik.fobres.repository.network.service.ServiceRating
import com.brazhnik.fobres.repository.storage.StorageRating

class RatingPresenter : MvpPresenter<RatingView>(), RatingView {

    private val listRating = MutableLiveData<List<Rating>>()
    var modelRatingHelper: ModelRatingHelper = ModelRatingHelper(StorageRating(), ServiceRating(), listRating)


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