package com.brazhnik.fobres.view.rating

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.brazhnik.fobres.repository.models.ModelRatingHelper
import com.brazhnik.fobres.repository.storage.StorageRating
import com.brazhnik.fobres.repository.viewmodel.VModelRating
import java.lang.Exception
import kotlinx.coroutines.*

@InjectViewState
class RatingPresenter(
    private val initParams: Any, // параметры для инициализации Presenter
) : MvpPresenter<RatingView>(), RatingView {

    private var storage: StorageRating = StorageRating()
    private var modelRatingHelper: ModelRatingHelper = ModelRatingHelper(storage)

    @DelicateCoroutinesApi
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        GlobalScope.launch {
            try {
                withContext(Dispatchers.Main) {
                    viewState.changeView("result") // обращение к View из Presenter

                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    e.message?.let { viewState.changeView(it) }
                }
            }
        }
    }

    fun doSomethingInPresenter(params: Any) {
        // do something in presenter
    }

    override fun changeView(parameter: Any) {
        print(parameter.toString())
    }

    override fun onError(error: String) {
        print(error.toString())
    }

    override fun getListUserRating(countItem: Int): ArrayList<VModelRating> {
        return modelRatingHelper.getListUserRating(countItem)
    }


}