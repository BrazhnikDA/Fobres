package com.brazhnik.fobres.view.rating

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
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
import com.brazhnik.fobres.utilities.displayToast

class RatingActivity : AppCompatActivity(), RatingView {

    @InjectPresenter
    lateinit var ratingPresenter: RatingPresenter

    @ProvidePresenter // данный метод нужен только если Presenter имеет параметры для инициализации
    fun providePresenter() = RatingPresenter()

    lateinit var mainAdapter: RatingAdapter
    lateinit var listUser: List<Rating>
    private var listUser2: MutableLiveData<List<Rating>> = MutableLiveData()

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rating)

        //ratingPresenter.listRating.observe(LifecycleOwner(this),)

        ratingPresenter = RatingPresenter()

        val recyclerView: RecyclerView = findViewById(R.id.listRating)
        recyclerView.layoutManager = LinearLayoutManager(this)

         fillList()
         listUser2.observe(this, Observer {
             recyclerView.adapter = listUser2.value?.let { it1 -> RatingAdapter(it1) }
         })

         /*listUser = getListUserRatingAPI()
         recyclerView.adapter = RatingAdapter(listUser)*/
    }

    private fun fillList() {
        listUser2 = getListUserRatingAPI()
    }

    override fun getListUserRatingAPI() : MutableLiveData<List<Rating>> {
        return (ratingPresenter.getListUserRatingAPI())
    }

    override fun getListUserRating(countItem: Int): List<Rating> {
        return ratingPresenter.getListUserRating(countItem)
    }
}