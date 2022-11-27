package com.brazhnik.fobres.view.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.brazhnik.fobres.R
import com.brazhnik.fobres.data.SharedData
import com.brazhnik.fobres.data.model.ProfileFull
import com.brazhnik.fobres.data.model.Token
import com.brazhnik.fobres.databinding.FragmentProfileBinding
import com.brazhnik.fobres.view.authorization.login.LoginActivity
import com.brazhnik.fobres.view.profile.editprofile.EditActivity
import com.squareup.picasso.Picasso


class ProfileFragment : Fragment(), ProfileView {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    @InjectPresenter
    lateinit var presenter: ProfilePresenter

    @ProvidePresenter
    fun providePresenter(): ProfilePresenter {
        return ProfilePresenter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // inflate the layout and bind to the _binding
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = providePresenter()

        // For view how guest or owner
        if (SharedData.isLogged) {
            showOwner()
        } else {
            showGuest()
        }
    }

    override fun onResume() {
        super.onResume()
        // For view how guest or owner
        if (SharedData.isLogged) {
            showOwner()
        } else {
            showGuest()
        }
    }

    private fun handlerButtonClick() {
        binding.changeProfile.setOnClickListener {
            val intent = Intent(context, EditActivity::class.java)
            startActivity(intent)
        }
        binding.imageProfile.setOnClickListener {
            with(binding.viewFullImage) {
                visibility = View.VISIBLE
            }
            binding.buttonBack.visibility = View.VISIBLE
        }
        binding.buttonBack.setOnClickListener {
            binding.viewFullImage.visibility = View.GONE
            binding.buttonBack.visibility = View.GONE
        }
        binding.buttonUnsignedProfile.setOnClickListener {
            presenter.removeCurrentTokenDB(Token(SharedData.profileFullCurrent.login, SharedData._userToken))
            SharedData._userToken = null.toString()
            SharedData.isLogged = false
            SharedData.profileFullCurrent

            val intent = Intent(context, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showOwner() {
        loadData()
        handlerButtonClick()
        binding.changeProfile.visibility = View.VISIBLE
        presenter.profileFull.observe(viewLifecycleOwner) {
            fillingFields(it)
            binding.layoutLoadBar.visibility = View.GONE
            SharedData.profileFullCurrent = it  // Update local profile
            //presenter.setProfileDB(it) // TODO FIX EXCEPTION FIELDS FOR PLACE IS NULL
        }

        presenter.status.observe(viewLifecycleOwner) {
            binding.title.text = resources.getString(R.string.offline)
            presenter.profileFull.postValue(SharedData.profileFullCurrent)
        }
    }

    private fun showGuest() {
        binding.changeProfile.visibility = View.GONE
        binding.errorLogged.visibility = View.VISIBLE
        binding.btnLogged.setOnClickListener {
            val intent = Intent(context, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun loadData() {
        // колесо загрузки
        binding.layoutLoadBar.visibility = View.VISIBLE
        presenter.getCurrentProfileAPI(SharedData.profileFullCurrent.id.toInt())
    }

    private fun fillingFields(profileFullInfo: ProfileFull) {
        binding.name.text = String.format(
            getString(R.string.name_profile),
            profileFullInfo.firstName,
            profileFullInfo.lastName
        )
        binding.status.text =
            String.format(getString(R.string.status_profile), profileFullInfo.status)
        binding.coins.text = String.format(getString(R.string.coins_profile), profileFullInfo.money)
        binding.description.text =
            String.format(
                getString(R.string.description_profile),
                profileFullInfo.profileDescription
            )
        binding.locationText.text = String.format(
            getString(R.string.location_profile),
            profileFullInfo.country,
            profileFullInfo.city
        )
        binding.title.text = String.format(getString(R.string.login_profile), profileFullInfo.login)
        binding.placeWorld.text =
            String.format(getString(R.string.place_profile), profileFullInfo.globalRating)
        binding.placeCountry.text =
            String.format(getString(R.string.place_profile), profileFullInfo.countryRating)
        binding.placeCity.text =
            String.format(getString(R.string.place_profile), profileFullInfo.cityRating)
        Picasso.get().load(profileFullInfo.profilePicture).into(binding.imageProfile)
        Picasso.get().load(profileFullInfo.profilePicture).into(binding.viewFullImage)
    }
}