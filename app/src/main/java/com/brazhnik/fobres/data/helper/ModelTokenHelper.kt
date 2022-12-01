package com.brazhnik.fobres.data.helper

import androidx.lifecycle.MutableLiveData
import com.brazhnik.fobres.data.database.room.repository.RoomTokenEventRepository
import com.brazhnik.fobres.data.model.Token

class ModelTokenHelper (
    private val token: MutableLiveData<Token>?
) {
    suspend fun setToken(token: Token) {
        RoomTokenEventRepository.saveToken(token)
    }

    suspend fun getToken() {
        token?.postValue(RoomTokenEventRepository.getToken()) ?: return
    }

    suspend fun deleteToken() {
        RoomTokenEventRepository.deleteToken()
    }
}