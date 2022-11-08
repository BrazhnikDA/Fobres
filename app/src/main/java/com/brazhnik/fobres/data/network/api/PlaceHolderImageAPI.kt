package com.brazhnik.fobres.data.network.api

import com.brazhnik.fobres.data.model.UpdateImageAnswer
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface PlaceHolderImageAPI {

    @Multipart
    @POST("/api/img/update/{id}")
    fun updateImageCurrentProfile(@Header("Authorization") token: String, @Path("id") id: String, @Part file: MultipartBody.Part): Call<UpdateImageAnswer>
}