package com.brazhnik.fobres.view.rating

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.MvpActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.brazhnik.fobres.R
import com.brazhnik.fobres.repository.viewmodel.VModelRating

class RatingActivity : MvpActivity(), RatingView {

    @InjectPresenter
    lateinit var ratingPresenter: RatingPresenter

    @ProvidePresenter // данный метод нужен только если Presenter имеет параметры для инициализации
    fun providePresenter() = RatingPresenter("initParams")

    private var mainAdapter: RatingAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rating)

        ratingPresenter = RatingPresenter(providePresenter())

        val recyclerView: RecyclerView = findViewById(R.id.listRating)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = RatingAdapter(fillList())
    }

    private fun fillList(): ArrayList<VModelRating> {
        return getListUserRating(35)
    }

    override fun changeView(parameter: Any) {
        Toast.makeText(this, parameter.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun onError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun getListUserRating(countItem: Int): ArrayList<VModelRating> {
        return ratingPresenter.getListUserRating(countItem)
    }
}