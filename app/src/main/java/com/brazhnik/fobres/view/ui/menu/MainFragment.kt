package com.brazhnik.fobres.view.ui.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.brazhnik.fobres.R
import com.brazhnik.fobres.data.SharedData
import com.brazhnik.fobres.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(SharedData.isLogged) {
            loadData()
        } else {
            showGuest()
        }
    }

    private fun loadData() {
        binding.textViewCoins.text =
            resources.getString(R.string.coins_home, SharedData.profileFullCurrent.money)
        binding.textViewTopWorld.text =
            resources.getString(R.string.world_home, "?")
        binding.textViewTopCountry.text =
            resources.getString(R.string.country_home, "?")
        binding.textViewTopCity.text =
            resources.getString(R.string.city_home, "?")
    }

    private fun showGuest() {
        // Поменять кнпоку гость / авторизация
        binding.textViewCoins.text =
            resources.getString(R.string.coins_home, "?")
        binding.textViewTopWorld.text =
            resources.getString(R.string.world_home, "?")
        binding.textViewTopCountry.text =
            resources.getString(R.string.country_home, "?")
        binding.textViewTopCity.text =
            resources.getString(R.string.city_home, "?")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}