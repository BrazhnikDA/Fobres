package com.brazhnik.fobres.data.network.service

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.brazhnik.fobres.data.model.Rating
import com.brazhnik.fobres.data.network.NetworkAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ServiceRating {
    fun getAllUsersAPI(
        result: MutableLiveData<List<Rating>>,
        status: MutableLiveData<String>
    ): MutableLiveData<List<Rating>> {
        NetworkAPI().getJSONRatingAPI().getAllUsers().enqueue(object : Callback<List<Rating>> {
            override fun onResponse(
                call: Call<List<Rating>>,
                response: Response<List<Rating>>
            ) {
                Log.e("Logs_response", response.body().toString())
                result.postValue(response.body())
                status.postValue("OK")
            }

            override fun onFailure(call: Call<List<Rating>>, t: Throwable) {
                Log.e("Logs_Error", t.toString())
                status.postValue("Error connection")
            }
        })
        return result
    }

    fun getCountryUsersAPI(
        result: MutableLiveData<List<Rating>>,
        country: String,
        status: MutableLiveData<String>
    ): MutableLiveData<List<Rating>> {
        NetworkAPI().getJSONRatingAPI().getCountryUsers(country)
            .enqueue(object : Callback<List<Rating>> {
                override fun onResponse(
                    call: Call<List<Rating>>,
                    response: Response<List<Rating>>
                ) {
                    Log.e("Logs_response", response.body().toString())
                    result.postValue(response.body())
                    status.postValue("OK")
                }

                override fun onFailure(call: Call<List<Rating>>, t: Throwable) {
                    Log.e("Logs_Error", t.toString())
                    status.postValue("Error connection")
                }
            })
        return result
    }

    fun getCityUsersAPI(
        result: MutableLiveData<List<Rating>>,
        city: String,
        status: MutableLiveData<String>
    ): MutableLiveData<List<Rating>> {
        NetworkAPI().getJSONRatingAPI().getCityUsers(city).enqueue(object : Callback<List<Rating>> {
            override fun onResponse(
                call: Call<List<Rating>>,
                response: Response<List<Rating>>
            ) {
                Log.e("Logs_response", response.body().toString())
                if (response.body() == null) {
                    result.postValue(
                        mutableListOf(
                            Rating(
                                "0",
                                "_",
                                "Your history is empty :(",
                                "_",
                                "_",
                                "_",
                                "_",
                                "_",
                                "0"
                            )
                        )
                    )
                    status.postValue(RESPONSE_EMPTY)
                } else {
                    result.postValue(response.body())
                    status.postValue(RESPONSE_OK)
                }
            }

            override fun onFailure(call: Call<List<Rating>>, error: Throwable) {
                Log.e("Logs_Error", error.message.toString())
                status.postValue(errorsHandler(error.toString()))
            }
        })
        return result
    }

    companion object {
        private const val RESPONSE_OK = "OK"
        private const val RESPONSE_FAILED = "Failed to connect"
        private const val RESPONSE_EMPTY = "Something went wrong"

        fun errorsHandler(error: String): String {
            if (error.contains("failed to connect", ignoreCase = true)) {
                return RESPONSE_FAILED
            }
            return ""
        }
    }
}
