package com.brazhnik.fobres.repository.storage

import com.brazhnik.fobres.repository.data.Rating

class StorageRating {
    // This fun testing and after few weeks i need added implementation for request to server and get response
    public fun getListOrders(countItem: Int): List<Rating> {
        val tmpList: ArrayList<Rating> = ArrayList()
        if (countItem > -1) {
            for (i in 0..50) {
                tmpList.add(
                    Rating(
                        id = i.toString(),
                        login = "1",
                        firstName = "1",
                        lastName = "2",
                        profileDescription = "3213",
                        profilePicture = "321",
                        country = "323",
                        city = "33333",
                        money = "123123123"
                    )
                )
            }
        } else {
            for (i in 0..countItem) {
                /*tmpList.add(
                    VModelRating(
                        name = "Dmitry Brazhnik",
                        amount = (10..3567).random().toString(),
                        placeRating = (i + 1).toString()
                    )
                )*/

            }
        }
        return tmpList.toList()
    }
}