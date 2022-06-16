package com.brazhnik.fobres.view.rating

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.MainThread
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.brazhnik.fobres.R
import com.brazhnik.fobres.data.model.Rating
import com.brazhnik.fobres.data.model.TypeRating
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.makeramen.roundedimageview.RoundedImageView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception


class RatingFragment : Fragment(), RatingView {
    @InjectPresenter
    lateinit var ratingPresenter: RatingPresenter

    //@ProvidePresenter // данный метод нужен только если Presenter имеет параметры для инициализации
    //fun providePresenter() = RatingPresenter(this.applicationContext)

    lateinit var mainAdapter: RatingAdapter
    private var listUser: MutableLiveData<List<Rating>> = MutableLiveData()
    private var statusResponse: MutableLiveData<String> = MutableLiveData()
    private lateinit var recyclerView: RecyclerView
    private var isLoad = false

    override suspend fun getRatingAllAPI() {
        delay(3000)
        ratingPresenter.getRatingAllAPI()
    }

    override suspend fun getRatingCityAPI(city: String) {
        delay(3000)
        ratingPresenter.getRatingCityAPI(city)
    }

    override suspend fun getRatingCountryAPI(country: String) {
        delay(3000)
        ratingPresenter.getRatingCountryAPI(country)
    }

    override suspend fun setRatingAllDB(listRating: List<Rating>) {
        ratingPresenter.setRatingAllDB(listRating)
    }

    override suspend fun getRatingAllDB(context: Context) {
        ratingPresenter.getRatingAllDB(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rating, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ratingPresenter =
            RatingPresenter(viewLifecycleOwner, view.context, listUser, statusResponse)

        recyclerView = view.findViewById(R.id.listRating)!!
        recyclerView.layoutManager = LinearLayoutManager(context)

        GlobalScope.launch {
            showLoadBar()
            handlerOptionRatingClick()
            createRequest(TypeRating.ALL, "")
        }

        // show error for user
        //
        listUser.observe(viewLifecycleOwner, Observer {
            recyclerView.adapter = listUser.value?.let { it1 ->
                RatingAdapter(it1)
            }
            if (listUser.value?.isNotEmpty() == true) {
                GlobalScope.launch {
                    listUser.value?.let { it1 -> setRatingAllDB(it1) }
                }
                stopLoadBar()
            }
        })

        statusResponse.observe(viewLifecycleOwner, Observer {
            statusResponse.value?.let { it1 -> Log.d("Response Rating", it1) }
            if(statusResponse.value == "Error connection") {
                view.findViewById<TextView>(R.id.title)?.text = "Offline"
                GlobalScope.launch {
                    //getRatingAllDB(view.context)!!
                    GlobalScope.launch(Dispatchers.Main) {
                        try {
                            if (listUser.value!!.isEmpty()) {
                                stopLoadBar()
                                view.findViewById<TextView>(R.id.errorData).visibility =
                                    View.VISIBLE
                            }
                        }catch (ex: Exception) {
                            stopLoadBar()
                            view.findViewById<TextView>(R.id.errorData).visibility =
                                View.VISIBLE
                        }
                    }
                }
            } else {
                if (listUser.value?.size == 0) {
                    view.findViewById<TextView>(R.id.errorData).visibility = View.VISIBLE
                } else {
                    view.findViewById<TextView>(R.id.errorData).visibility = View.GONE
                }
            }
        })
    }


    private fun handlerOptionRatingClick() {
        var typeRating = TypeRating.ALL
        val world = view?.findViewById<RoundedImageView>(R.id.optionWorld)
        val country = view?.findViewById<RoundedImageView>(R.id.optionCountry)
        val city = view?.findViewById<RoundedImageView>(R.id.optionCity)

        world?.setOnClickListener {
            typeRating = TypeRating.ALL

            view?.findViewById<TextView>(R.id.errorData)?.visibility = View.GONE

            world.setBackgroundColor(resources.getColor(R.color.teal_200))
            country?.background = R.color.blackOpaque20.toDrawable()
            city?.background = R.color.blackOpaque20.toDrawable()
            listUser.postValue(listOf())

            GlobalScope.launch {
                createRequest(typeRating, "")
            }
        }

        country?.setOnClickListener {
            typeRating = TypeRating.COUNTRY

            view?.findViewById<TextView>(R.id.errorData)?.visibility = View.GONE

            world?.background = R.color.blackOpaque20.toDrawable()
            country.setBackgroundColor(resources.getColor(R.color.teal_200))
            city?.background = R.color.blackOpaque20.toDrawable()
            listUser.postValue(listOf())

            GlobalScope.launch {
                createRequest(typeRating, "Россия")
            }
        }

        city?.setOnClickListener {
            typeRating = TypeRating.CITY

            view?.findViewById<TextView>(R.id.errorData)?.visibility = View.GONE

            world?.background = R.color.blackOpaque20.toDrawable()
            country?.background = R.color.blackOpaque20.toDrawable()
            city.setBackgroundColor(resources.getColor(R.color.teal_200))
            listUser.postValue(listOf())

            GlobalScope.launch {
                createRequest(typeRating, "Нижний Новгород")
            }
        }
    }

    @SuppressLint("SetTextI18n", "CutPasteId")
    private suspend fun createRequest(typeRating: TypeRating, body: String?) {
        GlobalScope.launch {
            showLoadBar()
        }
        if (typeRating == TypeRating.ALL) {
            getRatingAllAPI()
            view?.findViewById<TextView>(R.id.title)?.text =
                resources.getText(R.string.top_off_world)
        }
        if (typeRating == TypeRating.CITY) {
            if (body != null) {
                getRatingCityAPI(body)
            }
            view?.findViewById<TextView>(R.id.title)?.text =
                "${resources.getText(R.string.top_off)} $body"
        }
        if (typeRating == TypeRating.COUNTRY) {
            if (body != null) {
                getRatingCountryAPI(body)
            }
            view?.findViewById<TextView>(R.id.title)?.text =
                "${resources.getText(R.string.top_off)} $body"
        }
    }

    private suspend fun showLoadBar() {
        GlobalScope.launch(Dispatchers.Main) {
            isLoad = false
            val progressBar = view?.findViewById<CircularProgressIndicator>(R.id.loadBar)
            progressBar?.visibility = View.VISIBLE
            var progress = 0
            while (!isLoad) {
                if(progress == 100) progress = 0
                Log.d("Bar:", progress.toString())
                progressBar?.progress = progress
                progress += 5
                delay(45)
            }
        }
    }

    private fun stopLoadBar() {
        view?.findViewById<CircularProgressIndicator>(R.id.loadBar)?.visibility = View.GONE
        isLoad = true
    }
}