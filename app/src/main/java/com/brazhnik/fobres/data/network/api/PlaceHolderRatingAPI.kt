package com.brazhnik.fobres.data.network.api

import com.brazhnik.fobres.data.model.ShortUser
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PlaceHolderRatingAPI {
    @GET("/api/short-users/")
    fun getAllUsers(): Call<List<ShortUser>>

    @GET("/api/short-users/country/{country}")
    fun getCountryUsers(@Path("country") country: String): Call<List<ShortUser>>

    @GET("/api/short-users/city/{city}")
    fun getCityUsers(@Path("city") city: String): Call<List<ShortUser>>

    /*@POST("/api/auth/login")
    fun login(@Body request: LoginRequest?): Call<LoginResponse?>?*/
}