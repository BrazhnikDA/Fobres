package com.brazhnik.fobres.view.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.brazhnik.fobres.R
import com.brazhnik.fobres.data.model.Profile
import com.brazhnik.fobres.databinding.FragmentProfileBinding


class ProfileFragment : Fragment(), ProfileView {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    @InjectPresenter
    lateinit var presenter: ProfilePresenter

    @ProvidePresenter
    fun providePresenter(): ProfilePresenter {
        return ProfilePresenter(requireView().context)
    }

    private var id_: Int = 8

    private fun fillingFields(profileInfo: Profile) {
        binding.name.text = String.format(getString(R.string.name_profile), profileInfo.firstName, profileInfo.lastName)
        binding.coins.text = String.format(getString(R.string.coins_profile), profileInfo.money)
        binding.description.text = profileInfo.profileDescription
        binding.locationText.text = String.format(getString(R.string.location_profile), profileInfo.country, profileInfo.city)
        binding.title.text =  String.format(getString(R.string.login_profile), profileInfo.login)
    }

    override fun getCurrentProfileAPI(id: Int) {
        presenter.getCurrentProfileAPI(id)
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
        // inflate the layout and bind to the _binding
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = providePresenter()

        openFragment()

        presenter.profile.observe(viewLifecycleOwner, Observer {
            fillingFields(it)
        })


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun openFragment() {
        // колесо загрузки
        getCurrentProfileAPI(id_)
    }
}