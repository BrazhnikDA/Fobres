package com.brazhnik.fobres.data.network.service

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.brazhnik.fobres.data.model.AuthRequest
import com.brazhnik.fobres.data.model.AuthResponse
import com.brazhnik.fobres.data.model.RegistrationUser
import com.brazhnik.fobres.data.network.NetworkAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ServiceRegistration {
    fun registrationUser(result: MutableLiveData<AuthResponse>, request: RegistrationUser, status: MutableLiveData<String>) {
        NetworkAPI().getJSONRegistrationAPI()
            .registrationUser(request)
            .enqueue(object : Callback<AuthResponse> {
                override fun onResponse(
                    call: Call<AuthResponse>,
                    response: Response<AuthResponse>
                ) {
                    if (response.body() != null) {
                        result.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                    Log.e("Logs_Error", t.toString())
                    status.postValue(t.message)
                }
            })
    }
}