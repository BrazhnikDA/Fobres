package com.brazhnik.fobres.view.profile

import com.arellomobile.mvp.MvpView
import com.brazhnik.fobres.repository.data.Profile

interface ProfileView : MvpView {
    fun getProfileInfo() : Profile
    fun openHistoryDeposit()
    fun viewHowGuest()
    fun changeProfile()

    // API
    fun getCurrentProfile() : Profile
}