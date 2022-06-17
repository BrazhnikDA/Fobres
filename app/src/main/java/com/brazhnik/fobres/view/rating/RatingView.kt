package com.brazhnik.fobres.view.rating

import android.content.Context
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.brazhnik.fobres.data.model.Rating

/**
 * If fun contains in name "API" - this fun getting data from server else
 * data getting in local DB
 */

interface RatingView : MvpView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun displayList(listRating: List<Rating>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showError()

    @StateStrategyType(AddToEndSingleStrategy::class)
    suspend fun showLoadingWheel()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun disableLoadingWheel()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun disableError()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setTitle(title: String)
}