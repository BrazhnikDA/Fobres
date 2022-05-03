package com.brazhnik.fobres.repository.models

import com.brazhnik.fobres.repository.storage.StorageRating
import com.brazhnik.fobres.repository.viewmodel.VModelRating
import com.brazhnik.fobres.view.rating.RatingView

class ModelRatingHelper(private val storageRating: StorageRating) : RatingView {

    override fun changeView(parameter: Any) {
        print("123")
    }

    override fun onError(error: String) {
        print(error)
    }

    override fun getListUserRating(countItem: Int): ArrayList<VModelRating> {
        return storageRating.getListOrders(countItem)
    }
}