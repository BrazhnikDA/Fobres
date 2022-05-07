package com.brazhnik.fobres.repository.network

import android.util.Log
import com.brazhnik.fobres.repository.data.Rating
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RatingService {

    private var result: ArrayList<Rating> = ArrayList()

    fun getAllUsers(): ArrayList<Rating> {
        NetworkAPI().getJSONApi().getAllUsers().enqueue(object : Callback<List<Rating>> {
            override fun onResponse(call: Call<List<Rating>>, response: Response<List<Rating>>) {
                Log.e("Logs_response", response.body().toString())
                result = (response.body() as ArrayList<Rating>?)!!
                Log.e("Logs_response", response.body().toString())
            }

            override fun onFailure(call: Call<List<Rating>>, t: Throwable) {
                Log.e("Logs_Error", t.toString())
            }
        })
        return result
    }
}
