package com.brazhnik.fobres.repository.network.api

import com.brazhnik.fobres.repository.data.Profile
import com.brazhnik.fobres.repository.data.Rating
import retrofit2.Call
import retrofit2.http.GET

interface PlaceHolderRatingAPI {
    @GET("/api/users/")
    fun getAllUsers(): Call<List<Rating>>

    @GET("/api/profile")
    fun getCurrentProfile(): Call<Profile>

    /*@POST("/api/auth/login")
    fun login(@Body request: LoginRequest?): Call<LoginResponse?>?*/
}