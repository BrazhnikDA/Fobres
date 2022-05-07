package com.brazhnik.fobres.view.rating

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arellomobile.mvp.MvpPresenter
import com.brazhnik.fobres.repository.models.ModelRatingHelper
import com.brazhnik.fobres.repository.storage.StorageRating
import com.brazhnik.fobres.repository.data.Rating
import com.brazhnik.fobres.repository.network.service.ServiceRating

class RatingPresenter : MvpPresenter<RatingView>(), RatingView {

    val listRating = MutableLiveData<List<Rating>>()
    var modelRatingHelper: ModelRatingHelper = ModelRatingHelper(StorageRating(), ServiceRating(), listRating)


    override fun getListUserRatingAPI() : MutableLiveData<List<Rating>> {
        return modelRatingHelper.getListUserRatingAPI()
    }

    override fun getListUserRating(countItem: Int): List<Rating> {
        return modelRatingHelper.getListUserRating(countItem)
    }


}