package com.brazhnik.fobres.data.storage

import com.brazhnik.fobres.data.model.Profile

class StorageProfile {

    fun getProfileInfo(): Profile {
        return Profile(
            id = "0",
            firstName = "Dmitry",
            lastName = "Brazhnik",
            login = "M1ND",
            country = "Russia",
            city = "Volgograd",
            profilePicture = null.toString(),
            profileDescription = "I love dogs and cats, pls subscribe to my youtube channel and " +
                    "and please feedback about my videos in comments. Thanks!",
            money = "3500.72 $"
            //placeInRating = "1"
        )
    }

    //fun getCurrentProfile(): Profile = ServiceProfile().getCurrentProfile(3)
}