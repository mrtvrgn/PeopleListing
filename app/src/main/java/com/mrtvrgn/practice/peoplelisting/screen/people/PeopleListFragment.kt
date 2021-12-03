package com.mrtvrgn.practice.peoplelisting.screen.people

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar
import com.mrtvrgn.practice.peoplelisting.R
import com.mrtvrgn.practice.peoplelisting.model.Person

class PeopleListFragment : Fragment(), PeopleListView {

    private val presenter = PeopleListPresenter()
    private val adapter by lazy { PeopleListAdapter() }
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_people_list, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_people_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recycler = view.findViewById<RecyclerView>(R.id.recycler)
        recycler.adapter = adapter
        recycler.setOnScrollChangeListener { v, _, _, _, _ ->
            presenter.listScrolled(canScroll = v.canScrollVertically(1))
        }

        swipeRefreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.swipe_refresh)
        swipeRefreshLayout.setOnRefreshListener { presenter.refreshList() }

        presenter.setView(this)
        presenter.refreshList()
    }

    override fun setItems(items: List<Person>) {
        adapter.addPeople(items)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        presenter.refreshList()
        return super.onOptionsItemSelected(item)
    }

    override fun startLoading() {
        swipeRefreshLayout.post { swipeRefreshLayout.isRefreshing = true }
    }

    override fun stopLoading() {
        swipeRefreshLayout.post { swipeRefreshLayout.isRefreshing = false }
    }

    override fun showMessage(message: String) {
        Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG).show()
    }
}