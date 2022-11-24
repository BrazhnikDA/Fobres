package com.brazhnik.fobres.data.network.api

import com.brazhnik.fobres.data.model.Profile
import com.brazhnik.fobres.data.model.ProfileFull
import retrofit2.Call
import retrofit2.http.*

interface PlaceHolderProfileAPI {
    @GET("/api/users/{id}/")
    fun getCurrentProfile(@Header("Authorization") token: String, @Path("id")id: Int): Call<ProfileFull>

    @GET("/api/users/user-name/{login}")
    fun getCurrentProfile(@Header("Authorization") token: String, @Path("login")login: String): Call<ProfileFull>

    @PUT("/api/users")
    fun updateCurrentProfile(@Header("Authorization") token: String, @Body profileBody: Profile): Call<Profile>
}