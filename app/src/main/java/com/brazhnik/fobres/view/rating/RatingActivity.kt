package com.brazhnik.fobres.view.rating

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.MvpActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.brazhnik.fobres.R
import com.brazhnik.fobres.repository.data.Rating
import com.brazhnik.fobres.repository.data.TypeRating
import com.brazhnik.fobres.utilities.displayToast
import java.lang.StringBuilder

class RatingActivity : AppCompatActivity(), RatingView {

    @InjectPresenter
    lateinit var ratingPresenter: RatingPresenter

    @ProvidePresenter // данный метод нужен только если Presenter имеет параметры для инициализации
    fun providePresenter() = RatingPresenter()

    lateinit var mainAdapter: RatingAdapter
    private var listUser: MutableLiveData<List<Rating>> = MutableLiveData()
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rating)

        ratingPresenter = RatingPresenter()

        recyclerView = findViewById(R.id.listRating)
        recyclerView.layoutManager = LinearLayoutManager(this)

        fillList()
        listUser.observe(this, Observer {
            recyclerView.adapter = listUser.value?.let { it1 -> RatingAdapter(it1) }
        })
    }

    @SuppressLint("SetTextI18n")
    private fun fillList() {
         val type: TypeRating = TypeRating.CITY
         val body: String = "Нижний Новгород"
        if (type == TypeRating.ALL) {
            listUser = getListUserRatingAllAPI()
            findViewById<TextView>(R.id.title).text = resources.getText(R.string.top_off_world)
        }
        if (type == TypeRating.CITY) {
            listUser = getListUserRatingCityAPI(body)
            findViewById<TextView>(R.id.title).text =
                "${resources.getText(R.string.top_off)} $body"
        }
        if (type == TypeRating.COUNTRY) {
            listUser = getListUserRatingCountryAPI(body)
            findViewById<TextView>(R.id.title).text =
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
}