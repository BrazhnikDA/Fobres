package com.brazhnik.fobres.data.helper

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.brazhnik.fobres.data.database.room.repository.RoomLogEventRepository
import com.brazhnik.fobres.data.database.room.repository.RoomRatingEventRepository
import com.brazhnik.fobres.data.model.LogEvent
import com.brazhnik.fobres.data.model.Rating
import com.brazhnik.fobres.data.network.service.ServiceRating
import com.brazhnik.fobres.view.rating.RatingView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ModelRatingHelper (
    private val context: Context,
    private val listRating: MutableLiveData<List<Rating>>,
    private val statusResponse: MutableLiveData<String>
) {
    private val ratingService: ServiceRating = ServiceRating()

    //region API Call
    // Need add override fun in to string for $listRating API and DB
    fun getRatingAllAPI() {
        ratingService.getAllUsersAPI(listRating, statusResponse)
        saveLog(context, mutableListOf(), "getRatingAllAPI", null)
        /*listRating.observe(lifecycle, Observer {
            listRating.value?.let { it1 -> saveLog(context, it1.toList(), "getRatingAllAPI", null) }
            GlobalScope.launch {
                // Save the data every time if user create api call
                listRating.value?.let { it1 -> setRatingAllDB(it1.toList()) }
            }
        })*/
    }

    fun getRatingCityAPI(city: String) {
        ratingService.getCityUsersAPI(listRating, city, statusResponse)
        saveLog(context, mutableListOf(), "getRatingCityAPI", null)

        /*listRating.observe(lifecycle, Observer {
            listRating.value?.let { it1 -> saveLog(context, it1.toList(), "getRatingCityAPI", null) }
        })*/
    }

    fun getRatingCountryAPI(country: String) {
        ratingService.getCountryUsersAPI(listRating, country, statusResponse)
        saveLog(context, mutableListOf(), "getRatingCountryAPI", null)

        /*listRating.observe(lifecycle, Observer {
            listRating.value?.let { it1 -> saveLog(context, it1.toList(), "getRatingCountryAPI", null) }
        })*/
    }
    //endregion

    //region Room DB Call
    suspend fun setRatingAllDB(listRating: List<Rating>) {
        saveLog(context, listRating.toList(), "setRatingAllDB", null)
        RoomRatingEventRepository.saveListRating(context, listRating)
    }

    suspend fun getRatingAllDB(context: Context) {
        listRating.postValue(RoomRatingEventRepository.getRatingAll(context))
        saveLog(context, mutableListOf(), "getRatingAllDB", null)

        /*listRating.observe(lifecycle, Observer {
            listRating.value?.let { it1 -> saveLog(context, it1.toList(), "getRatingAllDB", null) }
        })*/
    }

    suspend fun getRatingCountryDB(context: Context, country: String) {
        listRating.postValue(RoomRatingEventRepository.getRatingCountry(context, country))
        saveLog(context, mutableListOf(), "getRatingCountryDB", null)

        /*listRating.observe(lifecycle, Observer {
            listRating.value?.let { it1 -> saveLog(context, it1.toList(), "getRatingAllDB", null) }
        })*/
    }

    suspend fun getRatingCityDB(context: Context, city: String) {
        listRating.postValue(RoomRatingEventRepository.getRatingCity(context, city))
        saveLog(context, mutableListOf(), "getRatingCityDB", null)

        /*listRating.observe(lifecycle, Observer {
            listRating.value?.let { it1 -> saveLog(context, it1.toList(), "getRatingAllDB", null) }
        })*/
    }
    //endregion

    companion object {
        private const val LOG_PLACE = "ModelRatingHelper"

        fun saveLog(context: Context, listRating: List<Rating>, name: String, error: String?) {
            GlobalScope.launch {
                RoomLogEventRepository.saveLog(
                    context, LogEvent(
                        LOG_PLACE,
                        System.currentTimeMillis(),
                        name,
                        listToString(listRating),
                        error ?: "null"
                    )
                )
            }
        }

        private fun listToString(listRating: List<Rating>): String {
            var result = ""
            for (item in listRating) {
                result += item.toString() + "\n"
            }
            return if (result != "") result else "Null"
        }
    }
}