package com.brazhnik.fobres.view.profile

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.arellomobile.mvp.presenter.InjectPresenter
import com.brazhnik.fobres.R
import com.brazhnik.fobres.repository.viewmodel.VModelProfile


class ProfileActivity : AppCompatActivity(), ProfileView {

    @InjectPresenter
    lateinit var presenter: ProfilePresenter

    private var mainAdapter: ProfileAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        presenter = ProfilePresenter()

        fillingFields()
    }

    @SuppressLint("SetTextI18n")
    private fun fillingFields() {
        val profileInfo = getCurrentProfile()

        val profileName = findViewById<TextView>(R.id.profileName)
        val profileNickName = findViewById<TextView>(R.id.profileNickName)
        val profileDonate = findViewById<TextView>(R.id.profileDonate)
        val profileRating = findViewById<TextView>(R.id.profileRating)
        val profileDescription = findViewById<TextView>(R.id.profileDescription)


        val profileNameBody = findViewById<TextView>(R.id.profileNameBody)
        val profileCountryBody = findViewById<TextView>(R.id.profileCountryBody)
        val profileCityBody = findViewById<TextView>(R.id.profileCityBody)
        val profileDescriptionBody = findViewById<TextView>(R.id.profileDescriptionBody)

        profileName.text = "${profileInfo.firstName} ${profileInfo.secondName}"
        profileNickName.text = profileInfo.nickName
        profileDonate.text = profileInfo.amountAll
        profileRating.text = profileInfo.placeInRating
        profileDescription.text = profileInfo.description

        profileNameBody.text = "${profileInfo.firstName} ${profileInfo.secondName}"
        profileCountryBody.text = profileInfo.country
        profileCityBody.text = profileInfo.city
        profileDescriptionBody.text = profileInfo.description
        /*val profileInfo = getProfileInfo()

        val profileName = findViewById<TextView>(R.id.profileName)
        val profileNickName = findViewById<TextView>(R.id.profileNickName)
        val profileDonate = findViewById<TextView>(R.id.profileDonate)
        val profileRating = findViewById<TextView>(R.id.profileRating)
        val profileDescription = findViewById<TextView>(R.id.profileDescription)


        val profileNameBody = findViewById<TextView>(R.id.profileNameBody)
        val profileCountryBody = findViewById<TextView>(R.id.profileCountryBody)
        val profileCityBody = findViewById<TextView>(R.id.profileCityBody)
        val profileDescriptionBody = findViewById<TextView>(R.id.profileDescriptionBody)

        profileName.text = "${profileInfo.firstName} ${profileInfo.secondName}"
        profileNickName.text = profileInfo.nickName
        profileDonate.text = profileInfo.amountAll
        profileRating.text = profileInfo.placeInRating
        profileDescription.text = profileInfo.description

        profileNameBody.text = "${profileInfo.firstName} ${profileInfo.secondName}"
        profileCountryBody.text = profileInfo.country
        profileCityBody.text = profileInfo.city
        profileDescriptionBody.text = profileInfo.description*/
    }

    override fun getProfileInfo(): VModelProfile {
        return presenter.getProfileInfo()
    }

    override fun getCurrentProfile(): VModelProfile {
        return presenter.getCurrentProfile()
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
}