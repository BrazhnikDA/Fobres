package com.brazhnik.fobres.data.network.api

import com.brazhnik.fobres.data.model.AuthRequest
import com.brazhnik.fobres.data.model.AuthResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface PlaceHolderLoginAPI {

    @POST("/api/auth/login")
    fun checkLoginSuccess(@Body login:AuthRequest): Call<AuthResponse>
}