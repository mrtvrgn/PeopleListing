package com.mrtvrgn.practice.peoplelisting.screen.people

import android.util.Log
import com.mrtvrgn.practice.peoplelisting.model.Person

class PeopleListPresenter {

    private var view: PeopleListView? = null
    private val model by lazy { PeopleListModel() }
    private var retryCount = 0

    fun setView(view: PeopleListView) {
        this.view = view
    }

    fun refreshList() {
        view?.startLoading()

        model.fetchPeople({ people ->
                              updateList(people)
                              view?.stopLoading()
                          }, { error ->
                              Log.e("FETCH_ERROR", error)

                              if (retryCount++ > 3) {
                                  retryCount = 0
                                  view?.showMessage("No content found. Please try again later.")
                                  view?.stopLoading()
                              } else {
                                  refreshList()
                              }
                          })
    }

    fun listScrolled(canScroll: Boolean) {
        if (!canScroll) {
            refreshList()
        }
    }

    private fun updateList(list: List<Person>) {
        if (list.isEmpty()) {
            view?.showMessage("FNo content found. Please refresh.")
        } else {
            view?.setItems(list)
        }
    }
}