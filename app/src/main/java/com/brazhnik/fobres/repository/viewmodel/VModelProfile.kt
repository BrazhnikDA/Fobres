package com.brazhnik.fobres.repository.viewmodel

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

class VModelProfile(
    val firstName: String,
    val secondName: String,
    val nickName: String,
    val country: String,
    val city: String,
    val imageAvatar: Image?,
    val description: String,
    val amountAll: String,
    val placeInRating: String
) : Serializable {
    override fun toString(): String {
        return "Name: $firstName $secondName <$nickName>\n" +
                "Place of residence: $country/$city\n" +
                "Amount: $amountAll\n" +
                "Description: $description"
    }
}