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

class ModelRatingHelper(
    viewLifecycleOwner: LifecycleOwner,
    private val context: Context,
    private val ratingService: ServiceRating,
    private val listRating: MutableLiveData<List<Rating>>,
    private val statusResponse: MutableLiveData<String>
) : RatingView {

    private val lifecycle = viewLifecycleOwner

    //region API Call
    // Need add override fun in to string for $listRating API and DB
    override suspend fun getRatingAllAPI() {
        listRating.postValue(ratingService.getAllUsersAPI(listRating, statusResponse).value)

        /*listRating.observe(lifecycle, Observer {
            listRating.value?.let { it1 -> saveLog(context, it1.toList(), "getRatingAllAPI", null) }
            GlobalScope.launch {
                // Save the data every time if user create api call
                listRating.value?.let { it1 -> setRatingAllDB(it1.toList()) }
            }
        })*/
    }

    override suspend fun getRatingCityAPI(city: String) {
        listRating.postValue(ratingService.getCityUsersAPI(listRating, city).value)

        /*listRating.observe(lifecycle, Observer {
            listRating.value?.let { it1 -> saveLog(context, it1.toList(), "getRatingCityAPI", null) }
        })*/
    }

    override suspend fun getRatingCountryAPI(country: String) {
        listRating.postValue(ratingService.getCountryUsersAPI(listRating, country).value)

        /*listRating.observe(lifecycle, Observer {
            listRating.value?.let { it1 -> saveLog(context, it1.toList(), "getRatingCountryAPI", null) }
        })*/
    }
    //endregion

    //region Room DB Call
    override suspend fun setRatingAllDB(listRating: List<Rating>) {
        saveLog(context, listRating.toList(), "setRatingAllDB", null)
        RoomRatingEventRepository.saveListRating(context, listRating)
    }

    override suspend fun getRatingAllDB(context: Context) {
        listRating.postValue(RoomRatingEventRepository.getListRating(context))

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
            return if(result != "") result else "Null"
        }
    }
}