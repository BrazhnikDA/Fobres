package com.brazhnik.fobres.data.helper

import androidx.lifecycle.MutableLiveData
import com.brazhnik.fobres.data.database.room.repository.RoomLogEventRepository
import com.brazhnik.fobres.data.database.room.repository.RoomRatingEventRepository
import com.brazhnik.fobres.data.model.LogEvent
import com.brazhnik.fobres.data.model.ShortUser
import com.brazhnik.fobres.data.network.service.ServiceRating
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ModelRatingHelper (
    private val listShortUser: MutableLiveData<List<ShortUser>>,
    private val statusResponse: MutableLiveData<String>
) {
    private val ratingService: ServiceRating = ServiceRating()

    //region API Call
    // Need add override fun in to string for $listRating API and DB
    fun getRatingAllAPI() {
        ratingService.getAllUsersAPI(listShortUser, statusResponse)
        saveLog(mutableListOf(), "getRatingAllAPI", null)
        /*listRating.observe(lifecycle, Observer {
            listRating.value?.let { it1 -> saveLog(context, it1.toList(), "getRatingAllAPI", null) }
            GlobalScope.launch {
                // Save the data every time if user create api call
                listRating.value?.let { it1 -> setRatingAllDB(it1.toList()) }
            }
        })*/
    }

    fun getRatingCityAPI(city: String) {
        ratingService.getCityUsersAPI(listShortUser, city, statusResponse)
        saveLog(mutableListOf(), "getRatingCityAPI", null)

        /*listRating.observe(lifecycle, Observer {
            listRating.value?.let { it1 -> saveLog(context, it1.toList(), "getRatingCityAPI", null) }
        })*/
    }

    fun getRatingCountryAPI(country: String) {
        ratingService.getCountryUsersAPI(listShortUser, country, statusResponse)
        saveLog(mutableListOf(), "getRatingCountryAPI", null)

        /*listRating.observe(lifecycle, Observer {
            listRating.value?.let { it1 -> saveLog(context, it1.toList(), "getRatingCountryAPI", null) }
        })*/
    }
    //endregion

    //region Room DB Call
    suspend fun setRatingAllDB(listShortUser: List<ShortUser>) {
        saveLog(listShortUser.toList(), "setRatingAllDB", null)
        RoomRatingEventRepository.saveListRating(listShortUser)
    }

    suspend fun getRatingAllDB() {
        listShortUser.postValue(RoomRatingEventRepository.getRatingAll())
        saveLog(mutableListOf(), "getRatingAllDB", null)

        /*listRating.observe(lifecycle, Observer {
            listRating.value?.let { it1 -> saveLog(context, it1.toList(), "getRatingAllDB", null) }
        })*/
    }

    suspend fun getRatingCountryDB(country: String) {
        listShortUser.postValue(RoomRatingEventRepository.getRatingCountry(country))
        saveLog(mutableListOf(), "getRatingCountryDB", null)

        /*listRating.observe(lifecycle, Observer {
            listRating.value?.let { it1 -> saveLog(context, it1.toList(), "getRatingAllDB", null) }
        })*/
    }

    suspend fun getRatingCityDB(city: String) {
        listShortUser.postValue(RoomRatingEventRepository.getRatingCity(city))
        saveLog(mutableListOf(), "getRatingCityDB", null)

        /*listRating.observe(lifecycle, Observer {
            listRating.value?.let { it1 -> saveLog(context, it1.toList(), "getRatingAllDB", null) }
        })*/
    }
    //endregion

    companion object {
        private const val LOG_PLACE = "ModelRatingHelper"

        fun saveLog(listShortUser: List<ShortUser>, name: String, error: String?) {
            GlobalScope.launch {
                RoomLogEventRepository.saveLog(
                    LogEvent(
                        LOG_PLACE,
                        System.currentTimeMillis(),
                        name,
                        listToString(listShortUser),
                        error ?: "null"
                    )
                )
            }
        }

        private fun listToString(listShortUser: List<ShortUser>): String {
            var result = ""
            for (item in listShortUser) {
                result += item.toString() + "\n"
            }
            return if (result != "") result else "Null"
        }
    }
}