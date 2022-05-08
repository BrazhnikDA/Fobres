package com.brazhnik.fobres.repository.network.service

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.brazhnik.fobres.repository.data.Rating
import com.brazhnik.fobres.repository.network.NetworkAPI
import com.brazhnik.fobres.repository.storage.StorageRating
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ServiceRating {
     fun getAllUsersAPI(result: MutableLiveData<List<Rating>>) : MutableLiveData<List<Rating>> {
            NetworkAPI().getJSONRatingAPI().getAllUsers().enqueue(object : Callback<List<Rating>> {
                override fun onResponse(
                    call: Call<List<Rating>>,
                    response: Response<List<Rating>>
                ) {
                    Log.e("Logs_response", response.body().toString())
                    result.postValue(response.body())
                }

                override fun onFailure(call: Call<List<Rating>>, t: Throwable) {
                    Log.e("Logs_Error", t.toString())
                }
            })
         return result
    }

    fun getCountryUsersAPI(result: MutableLiveData<List<Rating>>, country: String) : MutableLiveData<List<Rating>> {
        NetworkAPI().getJSONRatingAPI().getCountryUsers(country).enqueue(object : Callback<List<Rating>> {
            override fun onResponse(
                call: Call<List<Rating>>,
                response: Response<List<Rating>>
            ) {
                Log.e("Logs_response", response.body().toString())
                result.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Rating>>, t: Throwable) {
                Log.e("Logs_Error", t.toString())
            }
        })
        return result
    }

    fun getCityUsersAPI(result: MutableLiveData<List<Rating>>, city: String) : MutableLiveData<List<Rating>> {
        NetworkAPI().getJSONRatingAPI().getCityUsers(city).enqueue(object : Callback<List<Rating>> {
            override fun onResponse(
                call: Call<List<Rating>>,
                response: Response<List<Rating>>
            ) {
                Log.e("Logs_response", response.body().toString())
                result.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Rating>>, t: Throwable) {
                Log.e("Logs_Error", t.toString())
            }
        })
        return result
    }
}
