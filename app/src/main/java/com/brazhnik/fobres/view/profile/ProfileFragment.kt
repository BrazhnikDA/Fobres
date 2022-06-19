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

class ProfileFragment : Fragment(), ProfileView {
    @InjectPresenter
    lateinit var presenter: ProfilePresenter

    @ProvidePresenter
    fun providePresenter(): ProfilePresenter {
        return ProfilePresenter(requireView().context)
    }

    private var id_: Int = 8

    private fun fillingFields(profileInfo: Profile) {
        val name = view?.findViewById<TextView>(R.id.name)
        val coins = view?.findViewById<TextView>(R.id.coins)
        val description = view?.findViewById<TextView>(R.id.description)
        val location = view?.findViewById<TextView>(R.id.locationText)
        val login = view?.findViewById<TextView>(R.id.title)

        if (name != null) {
            name.text = String.format(getString(R.string.name_profile), profileInfo.firstName, profileInfo.lastName)
        }
        if (coins != null) {
            coins.text = String.format(getString(R.string.coins_profile), profileInfo.money)
        }
        if (description != null) {
            description.text = profileInfo.profileDescription
        }
        if (location != null) {
            location.text = String.format(getString(R.string.location_profile), profileInfo.country, profileInfo.city)
        }
        if(login != null) {
            login.text =  String.format(getString(R.string.login_profile), profileInfo.login)
        }
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
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = providePresenter()

        openFragment()

        presenter.profile.observe(viewLifecycleOwner, Observer {
            fillingFields(it)
        })
    }

    private fun openFragment() {
        // колесо загрузки
        getCurrentProfileAPI(id_)
    }
}