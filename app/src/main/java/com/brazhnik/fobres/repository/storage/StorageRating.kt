package com.brazhnik.fobres.repository.storage

import com.brazhnik.fobres.repository.network.RatingService
import com.brazhnik.fobres.repository.viewmodel.VModelRating

class StorageRating {

    val ratingService: RatingService = RatingService()

    // This fun testing and after few weeks i need added implementation for request to server and get response
    fun getListOrders(countItem: Int): ArrayList<VModelRating> {
        val tmpList: ArrayList<VModelRating> = arrayListOf()
        if (countItem == -1) {
            for (i in 0..50) {
                tmpList.toMutableList().add(
                    VModelRating(
                        name = "Dmitry Brazhnik",
                        amount = (10..3567).random().toString(),
                        placeRating = (i + 1).toString()
                    )
                )
            }
        } else {
            for (i in 0..countItem) {
                tmpList.add(
                    VModelRating(
                        name = "Dmitry Brazhnik",
                        amount = (10..3567).random().toString(),
                        placeRating = (i + 1).toString()
                    )
                )

            }
        }
        return sortWithAmount(tmpList)
    }

    fun getListOrdersAPI(countItem: Int): ArrayList<VModelRating> {
        //val tmpList: ArrayList<VModelRating> = ratingService.getAllUsers() as ArrayList<VModelRating>
        return sortWithAmount(ratingService.getAllUsers() as ArrayList<VModelRating>)
    }

    private fun sortWithAmount(listRatingV: ArrayList<VModelRating>): ArrayList<VModelRating> {
        val tmpListRatingV: ArrayList<VModelRating> =
            ArrayList(listRatingV.sortedWith(compareBy { it.amount }))
        for (i in tmpListRatingV.indices) {
            tmpListRatingV[i].placeRating = (i + 1).toString()
        }
        return tmpListRatingV
    }
}