package com.brazhnik.fobres.data

import com.brazhnik.fobres.data.model.ProfileFull

abstract class SharedData {
    companion object {
        private lateinit var _profileFullCurrent: ProfileFull
        lateinit var _userToken: String


        var profileFullCurrent: ProfileFull
            get() = _profileFullCurrent
            set(value) {
                _profileFullCurrent = value
            }

        var isLogged: Boolean = false


        //region CONSTANT
        const val TIME_WAIT_RESPONSE_API = 1500
        //endregion
    }
}