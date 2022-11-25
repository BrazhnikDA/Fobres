package com.brazhnik.fobres.data.database.repository

import com.brazhnik.fobres.data.model.Token

interface TokenEventRepository {
    suspend fun saveToken(token: Token)
    suspend fun getToken(): Token
    suspend fun deleteToken(token: Token)
}