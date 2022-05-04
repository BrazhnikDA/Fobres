package com.brazhnik.fobres.repository.network

import android.util.Log
import com.brazhnik.fobres.repository.viewmodel.VModelRating
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RatingService {

    private var result = ArrayList<VModelRating>()

    fun getAllUsers(): List<VModelRating> {
        NetworkAPI().getInstance()?.getJSONApi()?.getAllUsers("1")
            ?.enqueue(object : Callback<List<VModelRating?>?> {
                override fun onResponse(
                    call: Call<List<VModelRating?>?>,
                    response: Response<List<VModelRating?>?>
                ) {
                    result = response.body() as ArrayList<VModelRating>
                    Log.e("Logs_response", response.body().toString())
                }

                override fun onFailure(call: Call<List<VModelRating?>?>, t: Throwable) {
                    Log.e("Logs_Error", t.toString())
                }
            })
        return result
    }
}
