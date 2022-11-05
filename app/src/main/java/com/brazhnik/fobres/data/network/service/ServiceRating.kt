package com.brazhnik.fobres.data.network.service

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.brazhnik.fobres.data.SharedData
import com.brazhnik.fobres.data.model.ShortUser
import com.brazhnik.fobres.data.network.NetworkAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ServiceRating {
    fun getAllUsersAPI(
        result: MutableLiveData<List<ShortUser>>,
        status: MutableLiveData<String>
    ): MutableLiveData<List<ShortUser>> {
        NetworkAPI().getJSONRatingAPI().getAllUsers(SharedData._userToken).enqueue(object : Callback<List<ShortUser>> {
            override fun onResponse(
                call: Call<List<ShortUser>>,
                response: Response<List<ShortUser>>
            ) {
                Log.e("Logs_response", response.body().toString())
                if (response.body() == null) {
                    result.postValue(mutableListOf(EMPTY_LIST_RATING))
                    status.postValue(RESPONSE_EMPTY)
                } else {
                    result.postValue(response.body())
                    status.postValue(RESPONSE_OK)
                }
            }

            override fun onFailure(call: Call<List<ShortUser>>, t: Throwable) {
                Log.e("Logs_Error", t.toString())
                status.postValue("Error connection")
                result.postValue(mutableListOf(EMPTY_LIST_RATING))
            }
        })
        return result
    }

    fun getCountryUsersAPI(
        result: MutableLiveData<List<ShortUser>>,
        country: String,
        status: MutableLiveData<String>
    ): MutableLiveData<List<ShortUser>> {
        NetworkAPI().getJSONRatingAPI().getCountryUsers(SharedData._userToken, country)
            .enqueue(object : Callback<List<ShortUser>> {
                override fun onResponse(
                    call: Call<List<ShortUser>>,
                    response: Response<List<ShortUser>>
                ) {
                    Log.e("Logs_response", response.body().toString())
                    if (response.body() == null) {
                        result.postValue(mutableListOf(EMPTY_LIST_RATING))
                        status.postValue(RESPONSE_EMPTY)
                    } else {
                        result.postValue(response.body())
                        status.postValue(RESPONSE_OK)
                    }
                }

                override fun onFailure(call: Call<List<ShortUser>>, t: Throwable) {
                    Log.e("Logs_Error", t.toString())
                    status.postValue("Error connection")
                    result.postValue(mutableListOf(EMPTY_LIST_RATING))
                }
            })
        return result
    }

    fun getCityUsersAPI(
        result: MutableLiveData<List<ShortUser>>,
        city: String,
        status: MutableLiveData<String>
    ): MutableLiveData<List<ShortUser>> {
        NetworkAPI().getJSONRatingAPI().getCityUsers(SharedData._userToken, city).enqueue(object : Callback<List<ShortUser>> {
            override fun onResponse(
                call: Call<List<ShortUser>>,
                response: Response<List<ShortUser>>
            ) {
                Log.e("Logs_response", response.body().toString())
                if (response.body() == null) {
                    result.postValue(mutableListOf(EMPTY_LIST_RATING))
                    status.postValue(RESPONSE_EMPTY)
                } else {
                    result.postValue(response.body())
                    status.postValue(RESPONSE_OK)
                }
            }

            override fun onFailure(call: Call<List<ShortUser>>, error: Throwable) {
                Log.e("Logs_Error_onFailure", error.message.toString())
                status.postValue("Error connection")
                result.postValue(mutableListOf(EMPTY_LIST_RATING))
            }
        })
        return result
    }

    companion object {
        private const val RESPONSE_OK = "OK"
        private const val RESPONSE_FAILED = "Failed to connect"
        private const val RESPONSE_EMPTY = "Something went wrong"
        private val EMPTY_LIST_RATING = ShortUser(
            "0",
            "0",
            "_",
            "_",
            "_",
            "Your history is empty :("
        )

        fun errorsHandler(error: String): String {
            if (error.contains("failed to connect", ignoreCase = true)) {
                return RESPONSE_FAILED
            }
            return ""
        }
    }
}
