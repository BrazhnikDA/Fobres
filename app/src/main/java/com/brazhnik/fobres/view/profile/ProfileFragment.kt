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
import com.brazhnik.fobres.data.model.Profile
import com.brazhnik.fobres.databinding.FragmentProfileBinding
import com.brazhnik.fobres.view.authorization.login.LoginActivity


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

        if(SharedData.isLogged) {
            loadData()

            presenter.profile.observe(viewLifecycleOwner) {
                fillingFields(it)
                presenter.setProfileDB(it)
            }

            presenter.status.observe(viewLifecycleOwner) {
                binding.title.text = resources.getString(R.string.offline)
                presenter.getProfileDB()
            }
        } else {
            showGuest()
        }
    }

    private fun showGuest() {
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
        presenter.getCurrentProfileAPI(SharedData.profileCurrent.id.toInt())
    }

    private fun fillingFields(profileInfo: Profile) {
        binding.name.text = String.format(
            getString(R.string.name_profile),
            profileInfo.firstName,
            profileInfo.lastName
        )
        binding.coins.text = String.format(getString(R.string.coins_profile), profileInfo.money)
        binding.description.text = profileInfo.profileDescription
        binding.locationText.text = String.format(
            getString(R.string.location_profile),
            profileInfo.country,
            profileInfo.city
        )
        binding.title.text = String.format(getString(R.string.login_profile), profileInfo.login)
    }
}