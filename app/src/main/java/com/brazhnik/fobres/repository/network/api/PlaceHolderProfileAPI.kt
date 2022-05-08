package com.brazhnik.fobres.repository.network.api

import com.brazhnik.fobres.repository.data.Profile
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PlaceHolderProfileAPI {
    @GET("/api/users/{id}/")
    fun getCurrentProfile(@Path("id")id: Int): Call<Profile>

    @POST("/api/users/")
    fun updateCurrentProfile(@Path("id")id: Int, @Body profileBody: Profile)
}