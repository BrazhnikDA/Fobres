package com.brazhnik.fobres.data.model

/**
 * This class describes the model user profile
 *
 *  @param firstName - First name
 *  @param lastName - Second name
 *  @param login - NickName user for search
 *  @param country - User country
 *  @param city -  User city
 *  @param profilePicture - User image to profile
 *  @param profileDescription - User description to profile
 *  @param money - The general state of the user
 *
 *  @author Dmitriy Brazhnik
 */

class Profile(
    val id: Int,
    val login: String,
    val firstName: String,
    val lastName: String,
    val profileDescription: String,
    val status: String,
    val profilePicture: String,
    val country: String,
    val city: String,
    val money: Double
) {
    override fun toString(): String {
        return "Name: $firstName $lastName <$login>\n" +
                "Place of residence: $country/$city\n" +
                "Amount: $money\n" +
                "Status: $status\n" +
                "Description: $profileDescription"
    }
}