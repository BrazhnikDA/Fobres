package com.brazhnik.fobres.view.rating.showprofile

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.brazhnik.fobres.R
import com.brazhnik.fobres.data.SharedData
import com.brazhnik.fobres.data.model.ProfileFull
import com.brazhnik.fobres.databinding.ActivityEditBinding
import com.brazhnik.fobres.databinding.ActivityShowProfileBinding
import com.brazhnik.fobres.view.profile.editprofile.EditActivity
import com.brazhnik.fobres.view.profile.editprofile.EditPresenter
import com.squareup.picasso.Picasso

class ShowProfileActivity : AppCompatActivity(), ShowProfileView {
    private lateinit var binding: ActivityShowProfileBinding

    @InjectPresenter
    lateinit var showProfilePresenter: ShowProfilePresenter

    @ProvidePresenter
    fun providePresenter(): ShowProfilePresenter {
        return ShowProfilePresenter()
    }

    override fun onStart() {
        super.onStart()
        showProfilePresenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        showProfilePresenter.detachView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        showProfilePresenter.onDestroy()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityShowProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showProfilePresenter = providePresenter()

        val id = intent.extras!!["id"].toString()
        showProfilePresenter.getProfile(id)

        showProfilePresenter.profileFull.observe(this) {
            handlerButtonClick(it.profilePicture)
            fillFields(it)
            binding.layoutLoadBar.visibility = View.GONE
        }

        showProfilePresenter.status.observe(this) {
            binding.title.text = resources.getString(R.string.offline)
        }
    }

    private fun handlerButtonClick(imageURL: String) {
        binding.back.setOnClickListener {
            onBackPressed()
        }
        binding.imageProfile.setOnClickListener {
            Picasso.get().load(imageURL).into(binding.viewFullImage)

            binding.viewFullImage.visibility = View.VISIBLE
            binding.buttonBack.visibility = View.VISIBLE
        }
        binding.buttonBack.setOnClickListener {
            binding.viewFullImage.visibility = View.GONE
            binding.buttonBack.visibility = View.GONE
        }
    }

    override fun fillFields(profileFull: ProfileFull) {
        binding.name.text = String.format(
            getString(R.string.name_profile),
            profileFull.firstName,
            profileFull.lastName
        )
        binding.status.text =
            String.format(getString(R.string.status_profile), profileFull.status)
        binding.coins.text = String.format(getString(R.string.coins_profile), profileFull.money)
        binding.description.text =
            String.format(
                getString(R.string.description_profile),
                profileFull.profileDescription
            )
        binding.locationText.text = String.format(
            getString(R.string.location_profile),
            profileFull.country,
            profileFull.city
        )
        binding.title.text = String.format(getString(R.string.login_profile), profileFull.login)
        binding.placeWorld.text =
            String.format(getString(R.string.place_profile), profileFull.globalRating)
        binding.placeCountry.text =
            String.format(getString(R.string.place_profile), profileFull.countryRating)
        binding.placeCity.text =
            String.format(getString(R.string.place_profile), profileFull.cityRating)
        Picasso.get().load(profileFull.profilePicture).into(binding.imageProfile)
    }
}