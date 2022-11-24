package com.brazhnik.fobres.data.network.api

import com.brazhnik.fobres.data.model.AuthRequest
import com.brazhnik.fobres.data.model.AuthResponse
import com.brazhnik.fobres.data.model.RegistrationUser
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface PlaceHolderRegistrationAPI {

    @POST("/api/registration")
    fun registrationUser(@Body user: RegistrationUser): Call<AuthResponse>
}