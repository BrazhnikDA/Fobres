package com.brazhnik.fobres.data.model

data class RegistrationUser(
    var login: String,
    var firstName: String,
    var lastName: String,
    var country: String,
    var city: String,
    var password: String
) {
}