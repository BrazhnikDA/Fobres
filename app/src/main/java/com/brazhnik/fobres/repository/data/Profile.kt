package com.brazhnik.fobres.repository.data

import android.media.Image
import java.io.Serializable

/**
 * This class describes the model user profile
 *
 *  @param firstName - First name
 *  @param secondName - Second name
 *  @param nickName - NickName user for search
 *  @param country - User country
 *  @param city -  User city
 *  @param imageAvatar - User image to profile
 *  @param description - User description to profile
 *  @param amountAll - The general state of the user
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
    val money: String
) {
    override fun toString(): String {
        return "Name: $firstName $lastName <$login>\n" +
                "Place of residence: $country/$city\n" +
                "Amount: $money\n" +
                "Description: $profileDescription"
    }
}