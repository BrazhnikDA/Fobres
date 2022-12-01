package com.brazhnik.fobres.data.network.service

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.brazhnik.fobres.data.model.AuthRequest
import com.brazhnik.fobres.data.model.AuthResponse
import com.brazhnik.fobres.data.model.Profile
import com.brazhnik.fobres.data.model.ProfileFull
import com.brazhnik.fobres.data.network.NetworkAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ServiceLogin {
    fun checkLoginSuccess(
        result: MutableLiveData<AuthResponse>,
        request: AuthRequest,
        status: MutableLiveData<String>
    ) {
        NetworkAPI().getJSONLoginAPI()
            .checkLoginSuccess(request)
            .enqueue(object : Callback<AuthResponse> {
                override fun onResponse(
                    call: Call<AuthResponse>,
                    response: Response<AuthResponse>
                ) {
                    if (response.body() != null) {
                        result.postValue(response.body())
                    } else {
                        Log.e("Logs_Error", response.errorBody().toString())
                        status.postValue(response.message())
                    }
                }

                override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                    Log.e("Logs_Error", t.toString())
                    if(t.message!!.contains("failed to connect")) {
                        status.postValue("Failed to connect")
                    }
                    status.postValue(t.message)
                }
            })
    }
}