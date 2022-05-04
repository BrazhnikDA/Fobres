package com.brazhnik.fobres.view.profile

import com.arellomobile.mvp.MvpView
import com.brazhnik.fobres.repository.viewmodel.VModelProfile

interface ProfileView : MvpView {
    fun getProfileInfo() : VModelProfile
    fun openHistoryDeposit()
    fun viewHowGuest()
    fun changeProfile()
}