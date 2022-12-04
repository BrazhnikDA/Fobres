package com.brazhnik.fobres.data

import com.brazhnik.fobres.data.model.ProfileFull

abstract class SharedData {
    companion object {
        private lateinit var _profileFullCurrent: ProfileFull
        lateinit var _userToken: String


        lateinit var profileFullCurrent: ProfileFull

        var isLogged: Boolean = false


        //region CONSTANT
        const val TIME_WAIT_RESPONSE_API = 1500
        const val YANDEX_MOBILE_ADS_TAG = "YandexMobileAds"
        const val FULL_SCREEN_ADS_YANDEX = "R-M-DEMO-interstitial"
        //endregion
    }
}