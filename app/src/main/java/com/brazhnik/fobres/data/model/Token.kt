package com.brazhnik.fobres.data.model

data class Token(
    val login: String,
    val token: String
) {

    // Get token for API request
    override fun toString(): String {
        return "Bearer $token"
    }
}