package com.brazhnik.fobres.data.network.service

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.brazhnik.fobres.data.model.Profile
import com.brazhnik.fobres.data.network.NetworkAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ServiceProfile {
    fun getCurrentProfile(result: MutableLiveData<Profile>, id: Int, status: MutableLiveData<String>) : MutableLiveData<Profile> {
        NetworkAPI().getJSONProfileAPI().getCurrentProfile(id).enqueue(object : Callback<Profile> {
            override fun onResponse(
                call: Call<Profile>,
                response: Response<Profile>
            ) {
                Log.e("Logs_response", response.body().toString())
                result.postValue(response.body())
            }

            override fun onFailure(call: Call<Profile>, t: Throwable) {
                Log.e("Logs_Error", t.toString())
                status.postValue(t.message)
            }
        })
        return result
    }

}