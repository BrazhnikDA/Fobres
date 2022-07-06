package com.brazhnik.fobres.view.rating.showprofile

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.brazhnik.fobres.data.model.ProfileFull

interface ShowProfileView: MvpView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun fillFields(profileFull: ProfileFull)
}