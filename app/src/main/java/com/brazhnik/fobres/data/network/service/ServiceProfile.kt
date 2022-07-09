package com.brazhnik.fobres.data.network.service

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.brazhnik.fobres.data.model.Profile
import com.brazhnik.fobres.data.model.ProfileFull
import com.brazhnik.fobres.data.network.NetworkAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ServiceProfile {

    fun updateCurrentProfile(
        result: MutableLiveData<ProfileFull>,
        profileFull: ProfileFull,
        status: MutableLiveData<String>
    ) {
        val converter = Profile(
            id = profileFull.id.toInt(),
            login = profileFull.login,
            firstName = profileFull.firstName,
            lastName = profileFull.lastName,
            profileDescription = profileFull.profileDescription,
            status = profileFull.status,
            profilePicture = profileFull.profilePicture,
            country = profileFull.country,
            city = profileFull.city,
            money = profileFull.money.toDouble()
        )
        NetworkAPI().getJSONProfileAPI()
            .updateCurrentProfile(profileBody = converter)
            .enqueue(object : Callback<Profile> {
                override fun onResponse(
                    call: Call<Profile>,
                    response: Response<Profile>
                ) {
                    if (response.body() != null) {
                        result.postValue(
                            ProfileFull(
                                id = response.body()!!.id.toString(),
                                login = response.body()!!.login,
                                firstName = response.body()!!.firstName,
                                lastName = response.body()!!.lastName,
                                profileDescription = response.body()!!.profileDescription,
                                status = response.body()!!.status,
                                profilePicture = response.body()!!.profilePicture,
                                country = response.body()!!.country,
                                city = response.body()!!.city,
                                money = response.body()!!.money.toString(),
                                globalRating = profileFull.globalRating,
                                countryRating = profileFull.countryRating,
                                cityRating = profileFull.cityRating
                            )
                        )
                    }
                }

                override fun onFailure(call: Call<Profile>, t: Throwable) {
                    Log.e("Logs_Error", t.toString())
                    status.postValue(t.message)
                }
            })
    }

    fun getCurrentProfile(
        result: MutableLiveData<ProfileFull>,
        id: Int,
        status: MutableLiveData<String>
    ): MutableLiveData<ProfileFull> {
        NetworkAPI().getJSONProfileAPI().getCurrentProfile(id)
            .enqueue(object : Callback<ProfileFull> {
                override fun onResponse(
                    call: Call<ProfileFull>,
                    response: Response<ProfileFull>
                ) {
                    Log.e("Logs_response", response.body().toString())
                    result.postValue(response.body())
                }

                override fun onFailure(call: Call<ProfileFull>, t: Throwable) {
                    Log.e("Logs_Error", t.toString())
                    status.postValue(t.message)
                }
            })
        return result
    }
}