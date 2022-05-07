package com.brazhnik.fobres.view.profile

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.brazhnik.fobres.repository.models.ModelProfileHelper
import com.brazhnik.fobres.repository.storage.StorageProfile
import com.brazhnik.fobres.repository.data.Profile

@InjectViewState
class ProfilePresenter () : MvpPresenter<ProfileView>(), ProfileView {

    private var storage: StorageProfile = StorageProfile()
    private var modelProfileHelper: ModelProfileHelper = ModelProfileHelper(storage)

    override fun getProfileInfo(): Profile {
        return modelProfileHelper.getProfileInfo()
    }

    override fun openHistoryDeposit() {
        TODO("Not yet implemented")
    }

    override fun viewHowGuest() {
        TODO("Not yet implemented")
    }

    override fun changeProfile() {
        TODO("Not yet implemented")
    }

    override fun getCurrentProfile(): Profile {
        return modelProfileHelper.getCurrentProfile()
    }

}