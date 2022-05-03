package com.brazhnik.fobres.repository.storage

import com.brazhnik.fobres.repository.viewmodel.VModelProfile

class StorageProfile {

    fun getProfileInfo(): VModelProfile {
        return VModelProfile(
            firstName = "Dmitry",
            secondName = "Brazhnik",
            nickName = "M1ND",
            country = "Russia",
            city = "Volgograd",
            imageAvatar = null,
            description = "I love dogs and cats, pls subscribe to my youtube channel and " +
                    "and please feedback about my videos in comments. Thanks!",
            amountAll = "3500.72 $",
            placeInRating = "1"
        )
    }
}