package com.brazhnik.fobres.view.ui.menu

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.arellomobile.mvp.presenter.InjectPresenter
import com.brazhnik.fobres.R
import com.brazhnik.fobres.repository.data.Profile
import com.brazhnik.fobres.view.profile.ProfileAdapter
import com.brazhnik.fobres.view.profile.ProfilePresenter
import com.brazhnik.fobres.view.profile.ProfileView

class ProfileFragment : Fragment(), ProfileView {
    @InjectPresenter
    lateinit var presenter: ProfilePresenter

    private var mainAdapter: ProfileAdapter? = null

    private var profile: MutableLiveData<Profile> = MutableLiveData()
    private var id_: Int = 8

    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = ProfilePresenter()

        profile = getCurrentProfileAPI(id_)
        profile.observe(this, Observer {
            profile.value?.let { it1 -> fillingFields(it1) }
        })
    }

    @SuppressLint("SetTextI18n")
    private fun fillingFields(profileInfo: Profile) {
        /*val profileName = <TextView>(R.id.profileName)
        val profileNickName = findViewById<TextView>(R.id.profileNickName)
        val profileDonate = findViewById<TextView>(R.id.profileDonate)
        val profileRating = findViewById<TextView>(R.id.profileRating)
        val profileDescription = findViewById<TextView>(R.id.profileDescription)


        val profileNameBody = findViewById<TextView>(R.id.profileNameBody)
        val profileCountryBody = findViewById<TextView>(R.id.profileCountryBody)
        val profileCityBody = findViewById<TextView>(R.id.profileCityBody)
        val profileDescriptionBody = findViewById<TextView>(R.id.profileDescriptionBody)

        profileName.text = "${profileInfo.firstName} ${profileInfo.lastName}"
        profileNickName.text = profileInfo.login
        profileDonate.text = profileInfo.money.toString()
        //profileRating.text = profileInfo.placeInRating // Подумать как получать место в рейтинге
        profileDescription.text = profileInfo.profileDescription

        profileNameBody.text = "${profileInfo.firstName} ${profileInfo.lastName}"
        profileCountryBody.text = profileInfo.country
        profileCityBody.text = profileInfo.city
        profileDescriptionBody.text = profileInfo.profileDescription*/
    }

    override fun getCurrentProfileAPI(id: Int): MutableLiveData<Profile> {
        return presenter.getCurrentProfileAPI(id)
    }

    override fun getHistoryDepositAPI(id: Int) {
        return presenter.getHistoryDepositAPI(id)
    }

    override fun getViewHowGuestAPI(id: Int) {
        return presenter.getViewHowGuestAPI(id)
    }

    override fun updateProfileAPI(id: Int) {
        return presenter.updateProfileAPI(id)
    }

    override fun getCurrentProfileDB(id: Int): Profile {
        return presenter.getCurrentProfileDB(id)
    }

    override fun getHistoryDepositDB(id: Int) {
        return presenter.getHistoryDepositDB(id)
    }

    override fun getViewHowGuestDB(id: Int) {
        return presenter.getViewHowGuestDB(id)
    }

    override fun updateProfileDB(id: Int) {
        return presenter.updateProfileDB(id)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}