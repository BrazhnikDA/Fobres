package com.brazhnik.fobres.view.rating

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.brazhnik.fobres.repository.models.ModelRatingHelper
import com.brazhnik.fobres.repository.storage.StorageRating
import com.brazhnik.fobres.repository.data.Rating
import com.brazhnik.fobres.repository.network.service.ServiceRating

class RatingViewModel : ViewModel(), RatingView {

    private val listRating = MutableLiveData<List<Rating>>()
    var modelRatingHelper: ModelRatingHelper = ModelRatingHelper(StorageRating(), ServiceRating())

    override fun getListUserRatingAPI(countItem: Int) {
        modelRatingHelper.getListUserRatingAPI(countItem)
    }


    override fun getListUserRating(countItem: Int): List<Rating> {
        return modelRatingHelper.getListUserRating(countItem)
    }


}