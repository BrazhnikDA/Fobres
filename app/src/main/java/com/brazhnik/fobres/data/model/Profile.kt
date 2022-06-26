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
 *  @param placeInRating - User place in rating
 *
 *  @author Dmitry Brazhnik
 */

class Profile(
    val id: String,
    val login: String,
    val firstName: String,
    val lastName: String,
    val profileDescription: String,
    val profilePicture: String,
    val country: String,
    val city: String,
    val money: String,
    val worldPlace: String,
    val countryPlace: String,
    val cityPlace: String
) {
    override fun toString(): String {
        return "Name: $firstName $lastName <$login>\n" +
                "Place of residence: $country/$city\n" +
                "Amount: $money\n" +
                "Description: $profileDescription"
    }
}