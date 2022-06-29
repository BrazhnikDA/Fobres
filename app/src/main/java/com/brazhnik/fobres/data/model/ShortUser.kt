package com.brazhnik.fobres.data.model

class ShortUser(
    var id: String,
    var money: String,
    var firstName: String,
    var lastName: String,
    var profilePicture: String?,
    var status: String?
) {
    override fun toString(): String {
        val checkImage = if (profilePicture != "") "Use" else "Empty"
        return "id: $id, " +
                "firstName: $firstName, " +
                "lastName: $lastName, " +
                "status: $status" +
                "profileImage: $checkImage" +
                "money: $money\n"
    }
}