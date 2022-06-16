package com.brazhnik.fobres.view.rating

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.brazhnik.fobres.R
import com.brazhnik.fobres.data.model.Rating
import com.brazhnik.fobres.data.model.TypeRating
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class RatingFragment : Fragment(), RatingView {
    @InjectPresenter
    lateinit var ratingPresenter: RatingPresenter

    //@ProvidePresenter // данный метод нужен только если Presenter имеет параметры для инициализации
    //fun providePresenter() = RatingPresenter(this.applicationContext)

    lateinit var mainAdapter: RatingAdapter
    private var listUser: MutableLiveData<List<Rating>> = MutableLiveData()
    private var statusResponse: MutableLiveData<String> = MutableLiveData()
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("SetTextI18n", "CutPasteId")
    private suspend fun getDataAPI() {
        val type: TypeRating = TypeRating.ALL
        val body: String = "Нижний Новгород"
        if (type == TypeRating.ALL) {
            getRatingAllAPI()
            view?.findViewById<TextView>(R.id.title)?.text =
                resources.getText(R.string.top_off_world)
        }
        if (type == TypeRating.CITY) {
            getRatingCityAPI(body)
            view?.findViewById<TextView>(R.id.title)?.text =
                "${resources.getText(R.string.top_off)} $body"
        }
        if (type == TypeRating.COUNTRY) {
            getRatingCountryAPI(body)
            view?.findViewById<TextView>(R.id.title)?.text =
                "${resources.getText(R.string.top_off)} $body"
        }
    }

    override suspend fun getRatingAllAPI() {
         ratingPresenter.getRatingAllAPI()
    }

    override suspend fun getRatingCityAPI(city: String) {
         ratingPresenter.getRatingCityAPI(city)
    }

    override suspend fun getRatingCountryAPI(country: String) {
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

        ratingPresenter = RatingPresenter(viewLifecycleOwner, view.context, listUser, statusResponse)

        recyclerView = view.findViewById(R.id.listRating)!!
        recyclerView.layoutManager = LinearLayoutManager(context)

        GlobalScope.launch {
            getDataAPI()
            //delay(50)
        }

        // show error for user
        //
        listUser.observe(viewLifecycleOwner, Observer {
            recyclerView.adapter = listUser.value?.let { it1 ->
                RatingAdapter(it1)
            }
            if(view.findViewById<TextView>(R.id.title)?.text != "Offline") {
                GlobalScope.launch {
                    listUser.value?.let { it1 -> setRatingAllDB(it1) }
                }
            }
        })

        statusResponse.observe(viewLifecycleOwner, Observer {
            view.findViewById<TextView>(R.id.title)?.text = "Offline"
            GlobalScope.launch {
                getRatingAllDB(view.context)!!
               GlobalScope.launch(Dispatchers.Main) {
                    if (listUser.value!!.isEmpty()) {
                        view.findViewById<TextView>(R.id.errorData).visibility = View.VISIBLE
                    }
                }

                Log.d("Rating", "Offline mode")
                Log.d("list_user", listUser.value?.size.toString())
            }
        })
    }

}