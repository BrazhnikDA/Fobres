package com.brazhnik.fobres.view.profile

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.arellomobile.mvp.presenter.InjectPresenter
import com.brazhnik.fobres.R
import com.brazhnik.fobres.data.model.Profile

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
    }

    @SuppressLint("SetTextI18n")
    private fun fillingFields(profileInfo: Profile) {
        val profileName = view?.findViewById<TextView>(R.id.profileName)
        val profileNickName = view?.findViewById<TextView>(R.id.profileNickName)
        val profileDonate = view?.findViewById<TextView>(R.id.profileDonate)
        val profileRating = view?.findViewById<TextView>(R.id.profileRating)
        val profileDescription = view?.findViewById<TextView>(R.id.profileDescription)


        val profileNameBody = view?.findViewById<TextView>(R.id.profileNameBody)
        val profileCountryBody = view?.findViewById<TextView>(R.id.profileCountryBody)
        val profileCityBody = view?.findViewById<TextView>(R.id.profileCityBody)
        val profileDescriptionBody = view?.findViewById<TextView>(R.id.profileDescriptionBody)

        if (profileName != null) {
            profileName.text = "${profileInfo.firstName} ${profileInfo.lastName}"
        }
        if (profileNickName != null) {
            profileNickName.text = profileInfo.login
        }
        if (profileDonate != null) {
            profileDonate.text = profileInfo.money.toString()
        }
        //profileRating.text = profileInfo.placeInRating // Подумать как получать место в рейтинге
        if (profileDescription != null) {
            profileDescription.text = profileInfo.profileDescription
        }

        if (profileNameBody != null) {
            profileNameBody.text = "${profileInfo.firstName} ${profileInfo.lastName}"
        }
        if (profileCountryBody != null) {
            profileCountryBody.text = profileInfo.country
        }
        if (profileCityBody != null) {
            profileCityBody.text = profileInfo.city
        }
        if (profileDescriptionBody != null) {
            profileDescriptionBody.text = profileInfo.profileDescription
        }
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

        profile = getCurrentProfileAPI(id_)
        profile.observe(viewLifecycleOwner, Observer {
            profile.value?.let { it1 -> fillingFields(it1) }
        })
    }
}