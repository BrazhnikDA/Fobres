package com.brazhnik.fobres.view.rating

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.brazhnik.fobres.R
import com.brazhnik.fobres.data.helper.ModelRatingHelper
import com.brazhnik.fobres.data.model.Rating
import com.brazhnik.fobres.data.model.TypeRating
import com.brazhnik.fobres.data.network.service.ServiceRating
import com.brazhnik.fobres.databinding.FragmentProfileBinding
import com.brazhnik.fobres.databinding.FragmentRatingBinding
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.makeramen.roundedimageview.RoundedImageView
import kotlinx.coroutines.*


@DelicateCoroutinesApi
class RatingFragment : Fragment(), RatingView {
    private var _binding: FragmentRatingBinding? = null
    private val binding get() = _binding!!

    @InjectPresenter
    lateinit var ratingPresenter: RatingPresenter

    @ProvidePresenter
    fun providePresenter(): RatingPresenter {
        return RatingPresenter(requireView().context)
    }

    private lateinit var recyclerView: RecyclerView
    private var isLoad = false
    private var typeRating = TypeRating.ALL

    private fun getRatingAllAPI() {
        ratingPresenter.getRatingAllAPI()
    }

    private fun getRatingCityAPI(city: String) {
        ratingPresenter.getRatingCityAPI(city)
    }

    private fun getRatingCountryAPI(country: String) {
        ratingPresenter.getRatingCountryAPI(country)
    }

    private suspend fun setRatingAllDB(listRating: List<Rating>) {
        ratingPresenter.setRatingAllDB(listRating)
    }

    private suspend fun getRatingAllDB(context: Context) {
        ratingPresenter.getRatingAllDB(context)
    }

    private suspend fun getRatingCountryDB(context: Context, country: String) {
        ratingPresenter.getRatingCountryDB(context, country)
    }

    private suspend fun getRatingCityDB(context: Context, city: String) {
        ratingPresenter.getRatingCityDB(context, city)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRatingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ratingPresenter = providePresenter()

        recyclerView = view.findViewById(R.id.listRating)!!
        recyclerView.layoutManager = LinearLayoutManager(context)

        handlerButtonClick()
        handlerOptionRatingClick()
        GlobalScope.launch {
            createRequest(TypeRating.ALL, "")
        }

        ratingPresenter.listUser.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                recyclerView.adapter = RatingAdapter(it)
                if (view.findViewById<TextView>(R.id.title).text != "Offline") {
                    GlobalScope.launch {
                        setRatingAllDB(it)
                    }
                }
                disableLoadingWheel()
                disableError()
            }
        }

        ratingPresenter.statusResponse.observe(viewLifecycleOwner) {
           Log.d("Response Rating", it)
            if (it == "Error connection") {
                setTitle("Offline")
                GlobalScope.launch(Dispatchers.Main) {
                    try {
                        if (ratingPresenter.listUser.value!!.isEmpty()) {
                            disableLoadingWheel()
                            showError()
                        }
                    } catch (ex: Exception) {
                        disableLoadingWheel()
                        showError()
                    }
                }
            } else {
                if (ratingPresenter.listUser.value?.size == 0) {
                    showError()
                } else {
                    disableError()
                }
            }
        }
    }

    private fun handlerButtonClick() {
        binding.loadDataDB.setOnClickListener {
            GlobalScope.launch {
                disableError()
                showLoadingWheel()

                when (typeRating) {
                    TypeRating.ALL -> {
                        getRatingAllDB(requireView().context)
                    }
                    TypeRating.COUNTRY -> {
                        getRatingCountryDB(requireView().context, "????????????")
                    }
                    TypeRating.CITY -> {
                        getRatingCityDB(requireView().context, "???????????? ????????????????")
                    }
                }
            }
        }
    }

    private fun handlerOptionRatingClick() {
        val world = binding.optionWorld
        val country = binding.optionCountry
        val city = binding.optionCity

        world.setOnClickListener {
            if (!isLoad) return@setOnClickListener
            typeRating = TypeRating.ALL

            disableError()
            switchRating(world, country, city)

            GlobalScope.launch {
                createRequest(typeRating, "")
            }
        }

        country.setOnClickListener {
            if (!isLoad) return@setOnClickListener
            typeRating = TypeRating.COUNTRY

            disableError()
            switchRating(world, country, city)

            GlobalScope.launch {
                createRequest(typeRating, "????????????")
            }
        }

        city.setOnClickListener {
            if (!isLoad) return@setOnClickListener
            typeRating = TypeRating.CITY

            disableError()

            switchRating(world, country, city)

            GlobalScope.launch {
                createRequest(typeRating, "???????????? ????????????????")
            }
        }
    }

    override fun onResume() {
        super.onResume()
        switchRating(binding.optionWorld, binding.optionCountry, binding.optionCity)
    }

    private fun switchRating(
        world: RoundedImageView,
        country: RoundedImageView,
        city: RoundedImageView
    ) {
        when (typeRating) {
            TypeRating.ALL -> {
                world.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.teal_200))
                country.background = R.color.blackOpaque20.toDrawable()
                city.background = R.color.blackOpaque20.toDrawable()
            }
            TypeRating.COUNTRY -> {
                world.background = R.color.blackOpaque20.toDrawable()
                country.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.teal_200))
                city.background = R.color.blackOpaque20.toDrawable()
            }
            TypeRating.CITY -> {
                world.background = R.color.blackOpaque20.toDrawable()
                country.background = R.color.blackOpaque20.toDrawable()
                city.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.teal_200))
            }
        }
        ratingPresenter.listUser.postValue(listOf())
    }

    private suspend fun createRequest(typeRating: TypeRating, body: String?) {
        GlobalScope.launch {
            showLoadingWheel()
            if (typeRating == TypeRating.ALL) {
                setTitle(resources.getString(R.string.top_off_world))
                getRatingAllAPI()
            }
            if (typeRating == TypeRating.CITY) {
                if (body != null) {
                    setTitle(resources.getString(R.string.top_off) + " $body")
                    getRatingCityAPI(body)
                }
            }
            if (typeRating == TypeRating.COUNTRY) {
                if (body != null) {
                    setTitle(resources.getString(R.string.top_off) + " $body")
                    getRatingCountryAPI(body)
                }
            }
        }
    }

    override fun displayList(listRating: List<Rating>) {
        GlobalScope.launch(Dispatchers.Main) {
            recyclerView.adapter = RatingAdapter(listRating)
        }
    }

    override fun showError() {
        GlobalScope.launch(Dispatchers.Main) {
            binding.errorData.visibility = View.VISIBLE
            binding.loadDataDB.visibility = View.VISIBLE
        }
    }

    override fun disableError() {
        GlobalScope.launch(Dispatchers.Main) {
            binding.errorData.visibility = View.GONE
            binding.loadDataDB.visibility = View.GONE
        }
    }

    override fun setTitle(title: String) {
        GlobalScope.launch(Dispatchers.Main) {
            binding.title.text = title
            Log.d("Title:", title)
        }
    }

    override suspend fun showLoadingWheel() {
        GlobalScope.launch(Dispatchers.Main) {
            isLoad = false
            val progressBar = binding.loadBar
            progressBar.visibility = View.VISIBLE
            var progress = 0
            while (!isLoad) {
                if (progress == 100) progress = 0
                Log.d("Bar:", progress.toString())
                progressBar.progress = progress
                progress += 5
                delay(45)
            }
        }
    }

    override fun disableLoadingWheel() {
        binding.loadBar.visibility = View.GONE
        isLoad = true
    }
}