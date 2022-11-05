package com.brazhnik.fobres.data

interface LoginUseCase {
    fun checkAuth(login: String, password: String)
}