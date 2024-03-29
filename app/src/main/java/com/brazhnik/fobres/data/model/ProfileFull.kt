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
 *  @param globalRating - User place in world rating
 *  @param countryRating - User place in country rating
 *  @param cityRating - User place in city rating
 *
 *  @author Dmitriy Brazhnik
 */

data class ProfileFull(
    var id: String,
    var login: String,
    var firstName: String,
    var lastName: String,
    var profileDescription: String,
    var status: String,
    var profilePicture: String,
    var country: String,
    var city: String,
    var money: String,
    var globalRating: String,
    var countryRating: String,
    var cityRating: String
) {
    override fun toString(): String {
        return "Name: $firstName $lastName <$login>\n" +
                "Place of residence: $country/$city\n" +
                "Amount: $money\n" +
                "Status: $status\n" +
                "Description: $profileDescription" +
                "Picture: $profilePicture"
    }
}