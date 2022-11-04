package com.brazhnik.fobres.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

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

data class Profile(
    @SerializedName("id")
    val id: Int,

    @SerializedName("login")
    val login: String,

    @SerializedName("firstName")
    val firstName: String,

    @SerializedName("lastName")
    val lastName: String,

    @SerializedName("profileDescription")
    val profileDescription: String,

    @SerializedName("status")
    val status: String,

    @SerializedName("profilePicture")
    val profilePicture: String,

    @SerializedName("country")
    val country: String,

    @SerializedName("city")
    val city: String,

    @SerializedName("money")
    val money: Double
) : Serializable {
    override fun toString(): String {
        return "Name: $firstName $lastName <$login>\n" +
                "Place of residence: $country/$city\n" +
                "Amount: $money\n" +
                "Status: $status\n" +
                "Description: $profileDescription" +
                "Picture: $profilePicture"
    }
}