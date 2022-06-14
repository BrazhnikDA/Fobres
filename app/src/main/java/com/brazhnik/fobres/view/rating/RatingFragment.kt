package com.brazhnik.fobres.view.ui.menu

import android.annotation.SuppressLint
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
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.brazhnik.fobres.R
import com.brazhnik.fobres.repository.data.Rating
import com.brazhnik.fobres.repository.data.TypeRating
import com.brazhnik.fobres.utilities.displayToast
import com.brazhnik.fobres.view.rating.RatingAdapter
import com.brazhnik.fobres.view.rating.RatingPresenter
import com.brazhnik.fobres.view.rating.RatingView
import com.brazhnik.fobres.data.model.Rating
import com.brazhnik.fobres.data.model.TypeRating

class BackFragment : Fragment(), RatingView {
    @InjectPresenter
    lateinit var ratingPresenter: RatingPresenter

    //@ProvidePresenter // данный метод нужен только если Presenter имеет параметры для инициализации
    //fun providePresenter() = RatingPresenter(this.applicationContext)

    lateinit var mainAdapter: RatingAdapter
    private var listUser: MutableLiveData<List<Rating>> = MutableLiveData()
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ratingPresenter = RatingPresenter(this.applicationContext)

        context?.displayToast("CreateViewBack")
        Log.e("LOG","CreateViewBack")
    }

    @SuppressLint("SetTextI18n", "CutPasteId")
    private fun fillList() {
        val type: TypeRating = TypeRating.CITY
        val body: String = "Нижний Новгород"
        if (type == TypeRating.ALL) {
            listUser = getListUserRatingAllAPI()
            view?.findViewById<TextView>(R.id.title)?.text =
                resources.getText(R.string.top_off_world)
        }
        if (type == TypeRating.CITY) {
            listUser = getListUserRatingCityAPI(body)
            view?.findViewById<TextView>(R.id.title)?.text =
                "${resources.getText(R.string.top_off)} $body"
        }
        if (type == TypeRating.COUNTRY) {
            listUser = getListUserRatingCountryAPI(body)
            view?.findViewById<TextView>(R.id.title)?.text =
                "${resources.getText(R.string.top_off)} $body"
        }
    }

    override fun getListUserRatingAllAPI(): MutableLiveData<List<Rating>> {
        return ratingPresenter.getListUserRatingAllAPI()
    }

    override fun getListUserRatingCityAPI(city: String): MutableLiveData<List<Rating>> {
        return ratingPresenter.getListUserRatingCityAPI(city)
    }

    override fun getListUserRatingCountryAPI(country: String): MutableLiveData<List<Rating>> {
        return ratingPresenter.getListUserRatingCountryAPI(country)
    }

    override fun getListUserRatingAllDB(countItem: Int): List<Rating> {
        return ratingPresenter.getListUserRatingAllDB(countItem)
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
        recyclerView = view.findViewById(R.id.listRating)!!
        recyclerView.layoutManager = LinearLayoutManager(context)

        fillList()

        listUser.observe(viewLifecycleOwner, Observer {
            recyclerView.adapter = listUser.value?.let { it1 -> RatingAdapter(it1) }
        })
    }

}