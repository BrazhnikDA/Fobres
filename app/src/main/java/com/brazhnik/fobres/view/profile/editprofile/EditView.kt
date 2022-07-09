package com.brazhnik.fobres.view.profile.editprofile

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.brazhnik.fobres.data.model.ProfileFull

interface EditView: MvpView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun fillFields(profileFull: ProfileFull)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showError()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showLoadingWheel()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun hideLoadingWheel()
}