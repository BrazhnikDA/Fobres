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
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.makeramen.roundedimageview.RoundedImageView
import kotlinx.coroutines.*


@DelicateCoroutinesApi
class RatingFragment : Fragment(), RatingView {
    @InjectPresenter
    lateinit var ratingPresenter: RatingPresenter

    @ProvidePresenter
    fun providePresenter(): RatingPresenter {
        return RatingPresenter(
            ModelRatingHelper(
                viewLifecycleOwner,
                requireView().context,
                ServiceRating(),
                listUser,
                statusResponse
            )
        )
    }

    private var listUser: MutableLiveData<List<Rating>> = MutableLiveData()
    private var statusResponse: MutableLiveData<String> = MutableLiveData()
    private lateinit var recyclerView: RecyclerView
    private var isLoad = false
    private var typeRating = TypeRating.ALL

    private suspend fun getRatingAllAPI() {
        ratingPresenter.getRatingAllAPI()
    }

    private suspend fun getRatingCityAPI(city: String) {
        ratingPresenter.getRatingCityAPI(city)
    }

    private suspend fun getRatingCountryAPI(country: String) {
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
        return inflater.inflate(R.layout.fragment_rating, container, false)
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

        listUser.observe(viewLifecycleOwner) {
            recyclerView.adapter = listUser.value?.let { it1 ->
                RatingAdapter(it1)
            }
            if (listUser.value?.isNotEmpty() == true) {
                if (view.findViewById<TextView>(R.id.title).text != "Offline") {
                    GlobalScope.launch {
                        listUser.value?.let { it1 -> setRatingAllDB(it1) }
                    }
                }
                disableLoadingWheel()
                disableError()
            }
        }

        statusResponse.observe(viewLifecycleOwner) {
            statusResponse.value?.let { it1 -> Log.d("Response Rating", it1) }
            if (statusResponse.value == "Error connection") {
                setTitle("Offline")
                GlobalScope.launch(Dispatchers.Main) {
                    try {
                        if (listUser.value!!.isEmpty()) {
                            disableLoadingWheel()
                            showError()
                        }
                    } catch (ex: Exception) {
                        disableLoadingWheel()
                        showError()
                    }
                }
            } else {
                if (listUser.value?.size == 0) {
                    showError()
                } else {
                    disableError()
                }
            }
        }
    }

    private fun handlerButtonClick() {
        view?.findViewById<Button>(R.id.loadDataDB)?.setOnClickListener {
            GlobalScope.launch {
                disableError()
                showLoadingWheel()

                when (typeRating) {
                    TypeRating.ALL -> {
                        getRatingAllDB(requireView().context)
                    }
                    TypeRating.COUNTRY -> {
                        getRatingCountryDB(requireView().context, "Россия")
                    }
                    TypeRating.CITY -> {
                        getRatingCityDB(requireView().context, "Нижний новгород")
                    }
                }
            }
        }
    }

    private fun handlerOptionRatingClick() {
        val world = view?.findViewById<RoundedImageView>(R.id.optionWorld)
        val country = view?.findViewById<RoundedImageView>(R.id.optionCountry)
        val city = view?.findViewById<RoundedImageView>(R.id.optionCity)

        world?.setOnClickListener {
            if (!isLoad) return@setOnClickListener
            typeRating = TypeRating.ALL

            disableError()
            country?.let { it1 -> city?.let { it2 -> switchRating(world, it1, it2) } }

            GlobalScope.launch {
                createRequest(typeRating, "")
            }
        }

        country?.setOnClickListener {
            if (!isLoad) return@setOnClickListener
            typeRating = TypeRating.COUNTRY

            disableError()
            world?.let { it1 -> city?.let { it2 -> switchRating(it1, country, it2) } }

            GlobalScope.launch {
                createRequest(typeRating, "Россия")
            }
        }

        city?.setOnClickListener {
            if (!isLoad) return@setOnClickListener
            typeRating = TypeRating.CITY

            disableError()

            world?.let { it1 -> country?.let { it2 -> switchRating(it1, it2, city) } }

            GlobalScope.launch {
                createRequest(typeRating, "Нижний Новгород")
            }
        }
    }

    override fun onResume() {
        super.onResume()

        val world = view?.findViewById<RoundedImageView>(R.id.optionWorld)
        val country = view?.findViewById<RoundedImageView>(R.id.optionCountry)
        val city = view?.findViewById<RoundedImageView>(R.id.optionCity)
        world?.let { country?.let { it1 -> city?.let { it2 -> switchRating(it, it1, it2) } } }
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
        listUser.postValue(listOf())
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
            view?.findViewById<TextView>(R.id.errorData)?.visibility = View.VISIBLE
            view?.findViewById<Button>(R.id.loadDataDB)?.visibility = View.VISIBLE
        }
    }

    override fun disableError() {
        GlobalScope.launch(Dispatchers.Main) {
            view?.findViewById<TextView>(R.id.errorData)?.visibility = View.GONE
            view?.findViewById<Button>(R.id.loadDataDB)?.visibility = View.GONE
        }
    }

    override fun setTitle(title: String) {
        GlobalScope.launch(Dispatchers.Main) {
            view?.findViewById<TextView>(R.id.title)?.text = title
            Log.d("Title:", title)
        }
    }

    override suspend fun showLoadingWheel() {
        GlobalScope.launch(Dispatchers.Main) {
            isLoad = false
            val progressBar = view?.findViewById<CircularProgressIndicator>(R.id.loadBar)
            progressBar?.visibility = View.VISIBLE
            var progress = 0
            while (!isLoad) {
                if (progress == 100) progress = 0
                Log.d("Bar:", progress.toString())
                progressBar?.progress = progress
                progress += 5
                delay(45)
            }
        }
    }

    override fun disableLoadingWheel() {
        view?.findViewById<CircularProgressIndicator>(R.id.loadBar)?.visibility = View.GONE
        isLoad = true
    }
}