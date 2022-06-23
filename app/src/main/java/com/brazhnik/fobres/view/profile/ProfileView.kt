package com.brazhnik.fobres.view.profile

import androidx.lifecycle.MutableLiveData
import com.arellomobile.mvp.MvpView
import com.brazhnik.fobres.data.model.Profile

interface ProfileView : MvpView {
    // API
    fun getCurrentProfileAPI(id: Int)
    fun getHistoryDepositAPI(id: Int)
    fun getViewHowGuestAPI(id: Int)
    fun updateProfileAPI(id: Int)

    // DB
    fun getCurrentProfileDB(id: Int)
    fun getHistoryDepositDB(id: Int)
    fun getViewHowGuestDB(id: Int)
    fun updateProfileDB(id: Int)
}