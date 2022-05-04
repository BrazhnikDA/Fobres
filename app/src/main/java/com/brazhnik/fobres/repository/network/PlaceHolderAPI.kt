package com.brazhnik.fobres.repository.network

import com.brazhnik.fobres.repository.viewmodel.VModelProfile
import com.brazhnik.fobres.repository.viewmodel.VModelRating
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface PlaceHolderAPI {
    @GET("/api/users")
    fun getAllUsers(@Header("Authorization") token: String?): Call<List<VModelRating?>?>?

    @GET("/api/profile")
    fun getCurrentProfile(token: String?): Call<VModelProfile>

    /*@POST("/api/auth/login")
    fun login(@Body request: LoginRequest?): Call<LoginResponse?>?*/
}