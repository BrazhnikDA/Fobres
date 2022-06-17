package com.brazhnik.fobres.data.model

import android.graphics.drawable.ColorDrawable

class Rating(
    var id: String,
    var login: String,
    var firstName: String,
    var lastName: String,
    var profileDescription: String,
    var profilePicture: String?,
    var country: String,
    var city: String,
    var money: String
) {
    override fun toString(): String {
        val checkImage = if (profilePicture != "") "Use" else "Empty"
        return "id: $id, " +
                "login: $login, " +
                "country: $country" +
                "city: $city" +
                "firstName: $firstName, " +
                "lastName: $lastName, " +
                "profileDescription: $profileDescription," +
                "profileImage: $checkImage" +
                "money: $money\n"
    }
}