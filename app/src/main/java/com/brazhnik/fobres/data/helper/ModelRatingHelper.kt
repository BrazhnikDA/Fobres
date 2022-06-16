package com.brazhnik.fobres.data.helper

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.brazhnik.fobres.data.database.room.creator.LogDatabase
import com.brazhnik.fobres.data.database.room.repository.RoomLogEventRepository
import com.brazhnik.fobres.data.model.Rating
import com.brazhnik.fobres.data.network.service.ServiceRating
import com.brazhnik.fobres.data.storage.StorageRating
import com.brazhnik.fobres.view.rating.RatingView
import java.lang.Exception

class ModelRatingHelper(
    private val storageRating: StorageRating,
    private val ratingService: ServiceRating,
    private val list: MutableLiveData<List<Rating>>
) : RatingView {

    override  fun getListUserRatingAllAPI() : MutableLiveData<List<Rating>> {
        try {
            ratingService.getAllUsersAPI(list)
        } catch (ex: Exception) {
            Log.d("Error connection", ex.toString())
        }
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