package com.mrtvrgn.practice.peoplelisting.screen.people

import com.mrtvrgn.practice.peoplelisting.model.Person

interface PeopleListView {

    fun setItems(items: List<Person>)
    fun startLoading()
    fun stopLoading()
    fun showMessage(message: String)
}