package com.brazhnik.fobres.data.network.api

import com.brazhnik.fobres.data.model.Profile
import com.brazhnik.fobres.data.model.ProfileFull
import retrofit2.Call
import retrofit2.http.*

interface PlaceHolderProfileAPI {
    @GET("/api/users/{id}/")
    fun getCurrentProfile(@Path("id")id: Int): Call<ProfileFull>

    @PUT("/api/users")
    fun updateCurrentProfile(@Body profileBody: Profile): Call<Profile>
}