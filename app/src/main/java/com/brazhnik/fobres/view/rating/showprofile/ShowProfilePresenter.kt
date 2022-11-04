package com.brazhnik.fobres.view.rating.showprofile

import androidx.lifecycle.MutableLiveData
import com.arellomobile.mvp.MvpPresenter
import com.brazhnik.fobres.data.helper.ModelProfileHelper
import com.brazhnik.fobres.data.model.ProfileFull
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShowProfilePresenter: MvpPresenter<ShowProfileView>() {

    val profileFull: MutableLiveData<ProfileFull> = MutableLiveData()
    val status: MutableLiveData<String> = MutableLiveData()

    private val scope = CoroutineScope(Dispatchers.IO)

    private var modelProfileHelper: ModelProfileHelper =
        ModelProfileHelper(profileFull, status)

    fun getProfile(id: String) {
        scope.launch {
            modelProfileHelper.getCurrentProfileAPI(id.toInt())
        }
    }
}