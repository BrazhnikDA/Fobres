package com.brazhnik.fobres.repository.network

import android.util.Log
import com.brazhnik.fobres.repository.viewmodel.VModelProfile
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileService {

    private lateinit var result: VModelProfile

    fun getCurrentProfile(): VModelProfile {
        NetworkAPI().getInstance()?.getJSONApi()?.getCurrentProfile("1")
            ?.enqueue(object : Callback<VModelProfile> {
                override fun onResponse(
                    call: Call<VModelProfile>,
                    response: Response<VModelProfile>
                ) {
                    result = response.body() as VModelProfile
                    Log.e("Logs_response", response.body().toString())
                }

                override fun onFailure(call: Call<VModelProfile>, t: Throwable) {
                    Log.e("Logs_Error", t.toString())
                }
            })
        return result
    }
}