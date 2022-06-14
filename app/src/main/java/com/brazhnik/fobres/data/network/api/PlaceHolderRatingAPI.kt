package com.brazhnik.fobres.data.network.api

import com.brazhnik.fobres.data.model.Rating
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PlaceHolderRatingAPI {
    @GET("/api/users/")
    fun getAllUsers(): Call<List<Rating>>

    @GET("/api/users/country/{country}")
    fun getCountryUsers(@Path("country") country: String): Call<List<Rating>>

    @GET("/api/users/city/{city}")
    fun getCityUsers(@Path("city") city: String): Call<List<Rating>>

    /*@POST("/api/auth/login")
    fun login(@Body request: LoginRequest?): Call<LoginResponse?>?*/
}