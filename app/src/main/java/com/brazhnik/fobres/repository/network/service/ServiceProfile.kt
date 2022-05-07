package com.brazhnik.fobres.repository.network.service

import android.util.Log
import com.brazhnik.fobres.repository.data.Profile
import com.brazhnik.fobres.repository.network.NetworkAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ServiceProfile {

    private lateinit var result: Profile

    fun getCurrentProfile(): Profile {
        NetworkAPI().getInstance().getJSONApi().getCurrentProfile()
            .enqueue(object : Callback<Profile> {
                override fun onResponse(
                    call: Call<Profile>,
                    response: Response<Profile>
                ) {
                    result = response.body() as Profile
                    Log.e("Logs_response", response.body().toString())
                }

                override fun onFailure(call: Call<Profile>, t: Throwable) {
                    Log.e("Logs_Error", t.toString())
                }
            })
        return result
    }
}