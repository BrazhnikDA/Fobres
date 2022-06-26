package com.brazhnik.fobres.view.rating

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.brazhnik.fobres.R
import com.brazhnik.fobres.data.SharedData
import com.brazhnik.fobres.data.model.Rating
import com.brazhnik.fobres.data.model.TypeRating
import com.brazhnik.fobres.databinding.FragmentRatingBinding
import com.makeramen.roundedimageview.RoundedImageView
import kotlinx.coroutines.*

class RatingFragment : Fragment(), RatingView {
    private var _binding: FragmentRatingBinding? = null
    private val binding get() = _binding!!

    @InjectPresenter
    lateinit var ratingPresenter: RatingPresenter

    @ProvidePresenter
    fun providePresenter(): RatingPresenter {
        return RatingPresenter()
    }

    private val scope = CoroutineScope(Dispatchers.IO)

    private lateinit var recyclerView: RecyclerView
    private var isLoad = false
    private var typeRating = TypeRating.ALL

    var country: String = SharedData.profileCurrent.country
    var city: String = SharedData.profileCurrent.city

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
        scope.launch {
            createRequest(TypeRating.ALL, "")
        }

        ratingPresenter.listUser.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                displayList(it)
                if (binding.title.text != resources.getString(R.string.offline)) {
                    scope.launch {
                        ratingPresenter.setRatingAllDB(it)
                    }
                }
            }
        }

        ratingPresenter.statusResponse.observe(viewLifecycleOwner) {
            Log.d("Response Rating", it)

            if (it == "Error connection") {
                setTitle(resources.getString(R.string.offline))
                disableLoadingWheel()
                showError()
            }
        }
    }

    private fun handlerButtonClick() {
        binding.loadDataDB.setOnClickListener {
            disableError()
            showLoadingWheel()
            scope.launch {
                val task = launch {
                    when (typeRating) {
                        TypeRating.ALL -> {
                            ratingPresenter.getRatingAllDB()
                        }
                        TypeRating.COUNTRY -> {
                            ratingPresenter.getRatingCountryDB(country)
                        }
                        TypeRating.CITY -> {
                            ratingPresenter.getRatingCityDB(city)
                        }
                    }
                }
                task.join()
                disableLoadingWheel()
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

            scope.launch {
                createRequest(typeRating, "")
            }
        }

        country.setOnClickListener {
            if (!isLoad) return@setOnClickListener
            typeRating = TypeRating.COUNTRY

            disableError()
            switchRating(world, country, city)

            scope.launch {
                createRequest(typeRating, RatingFragment().country)
            }
        }

        city.setOnClickListener {
            if (!isLoad) return@setOnClickListener
            typeRating = TypeRating.CITY

            disableError()
            switchRating(world, country, city)

            scope.launch {
                createRequest(typeRating, RatingFragment().city)
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
                world.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.teal_200
                    )
                )
                country.background = R.color.blackOpaque20.toDrawable()
                city.background = R.color.blackOpaque20.toDrawable()
            }
            TypeRating.COUNTRY -> {
                world.background = R.color.blackOpaque20.toDrawable()
                country.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.teal_200
                    )
                )
                city.background = R.color.blackOpaque20.toDrawable()
            }
            TypeRating.CITY -> {
                world.background = R.color.blackOpaque20.toDrawable()
                country.background = R.color.blackOpaque20.toDrawable()
                city.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.teal_200
                    )
                )
            }
        }
    }

    private fun createRequest(typeRating: TypeRating, body: String?) {
        showLoadingWheel()
        if (typeRating == TypeRating.ALL) {
            setTitle(resources.getString(R.string.top_off_world))
            ratingPresenter.getRatingAllAPI()
        }
        if (typeRating == TypeRating.CITY) {
            if (body != null) {
                setTitle(resources.getString(R.string.top_off) + " $body")
                ratingPresenter.getRatingCityAPI(body)
            }
        }
        if (typeRating == TypeRating.COUNTRY) {
            if (body != null) {
                setTitle(resources.getString(R.string.top_off) + " $body")
                ratingPresenter.getRatingCountryAPI(body)
            }
        }
    }

    override fun displayList(listRating: List<Rating>) {
        recyclerView.adapter = RatingAdapter(listRating)
    }

    override fun showError() {
        disableLoadingWheel()
        binding.errorData.visibility = View.VISIBLE
        binding.loadDataDB.visibility = View.VISIBLE
        isLoad = true
    }

    override fun disableError() {
        disableLoadingWheel()
        binding.errorData.visibility = View.GONE
        binding.loadDataDB.visibility = View.GONE
        isLoad = true
    }

    override fun setTitle(title: String) {
        binding.title.text = title
        Log.d("Title:", title)
    }

    override fun showLoadingWheel() {
        scope.launch(Dispatchers.Main) {
            binding.loadBar.visibility = View.VISIBLE
            isLoad = false
        }
    }

    override fun disableLoadingWheel() {
        scope.launch(Dispatchers.Main) {
            binding.loadBar.visibility = View.GONE
            isLoad = true
        }
    }
}