package com.brazhnik.fobres.repository.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.brazhnik.fobres.repository.storage.StorageRating
import com.brazhnik.fobres.repository.data.Rating
import com.brazhnik.fobres.repository.network.service.ServiceRating
import com.brazhnik.fobres.view.rating.RatingView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class ModelRatingHelper(
    private val storageRating: StorageRating,
    private val ratingService: ServiceRating,
    private val list: MutableLiveData<List<Rating>>
) : RatingView {

    override  fun getListUserRatingAPI() : MutableLiveData<List<Rating>> {
        return ratingService.getAllUsers(list)
    }


    override fun getListUserRating(countItem: Int): List<Rating> {
        return storageRating.getListOrders(countItem)
    }
}