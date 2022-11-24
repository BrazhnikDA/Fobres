package com.brazhnik.fobres.data.model

data class AuthResponse(
    val username: String,
    val token: String,
    val roles: List<String>
)