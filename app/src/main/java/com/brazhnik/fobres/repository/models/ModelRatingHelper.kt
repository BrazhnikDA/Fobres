package com.brazhnik.fobres.repository.models

import androidx.lifecycle.MutableLiveData
import com.brazhnik.fobres.repository.data.Rating
import com.brazhnik.fobres.repository.network.service.ServiceRating
import com.brazhnik.fobres.repository.storage.StorageRating
import com.brazhnik.fobres.view.rating.RatingView

class ModelRatingHelper(
    private val storageRating: StorageRating,
    private val ratingService: ServiceRating,
    private val list: MutableLiveData<List<Rating>>
) : RatingView {

    override  fun getListUserRatingAllAPI() : MutableLiveData<List<Rating>> {
        return ratingService.getAllUsersAPI(list)
    }

    override fun getListUserRatingCityAPI(city: String): MutableLiveData<List<Rating>> {
        return ratingService.getCityUsersAPI(list, city)
    }

    override fun getListUserRatingCountryAPI(country: String): MutableLiveData<List<Rating>> {
        return ratingService.getCountryUsersAPI(list, country)
    }


    override fun getListUserRatingAllDB(countItem: Int): List<Rating> {
        return storageRating.getAllUsersDB(countItem)
    }
}