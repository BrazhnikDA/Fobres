package com.brazhnik.fobres.data

import com.brazhnik.fobres.data.model.Profile

abstract class SharedData {
    companion object {
        private lateinit var _profileCurrent: Profile

        var profileCurrent: Profile
            get() = _profileCurrent
            set(value) {
                _profileCurrent = value
            }

        var isLogged: Boolean = false


        //region CONSTANT
        const val TIME_WAIT_RESPONSE_API = 1500
        //endregion
    }
}