package com.brazhnik.fobres.view.rating

import com.arellomobile.mvp.MvpView
import com.brazhnik.fobres.repository.viewmodel.VModelRating

interface RatingView : MvpView {
    fun changeView(parameter: Any)
    fun onError(error: String)
    fun getListUserRating(countItem : Int) : List<VModelRating>
}