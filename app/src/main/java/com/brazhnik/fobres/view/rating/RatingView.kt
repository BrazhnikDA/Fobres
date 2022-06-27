package com.brazhnik.fobres.view.rating

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.brazhnik.fobres.data.model.ShortUser

/**
 * If fun contains in name "API" - this fun getting data from server else
 * data getting in local DB
 */

interface RatingView : MvpView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun displayList(listShortUser: List<ShortUser>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showError()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showLoadingWheel()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun disableLoadingWheel()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun disableError()

    fun setTitle(title: String)
}