package com.mrtvrgn.practice.peoplelisting.screen.people

import com.mrtvrgn.practice.peoplelisting.model.Person
import com.mrtvrgn.practice.peoplelisting.utils.DataSource

class PeopleListModel {

    private val dataSource = DataSource()
    private var nextPage: String? = null

    fun fetchPeople(onComplete: (people: List<Person>) -> Unit, onError: (message: String) -> Unit) {
        dataSource.fetch(nextPage) { response, error ->
            if (response != null) {
                nextPage = response.next

                //People with same `fullName` but different 'id's are allowed.
                onComplete(response.people.distinctBy { it.id to it.fullName })
            } else {
                onError(error?.errorDescription ?: "Fetching people failed.")
            }
        }
    }
}