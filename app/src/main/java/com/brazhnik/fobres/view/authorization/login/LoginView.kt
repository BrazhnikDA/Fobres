package com.brazhnik.fobres.view.authorization.login

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.brazhnik.fobres.data.model.ProfileFull

interface LoginView: MvpView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun checkLoginSuccess()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showError()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showLoadingWheel()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun hideLoadingWheel()
}