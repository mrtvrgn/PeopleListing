package com.mrtvrgn.practice.peoplelisting.screen.people

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mrtvrgn.practice.peoplelisting.R
import com.mrtvrgn.practice.peoplelisting.model.Person

class PeopleListAdapter : RecyclerView.Adapter<PeopleListAdapter.PeopleListViewHolder>() {

    private val people = mutableListOf<Person>()

    class PeopleListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById<TextView>(R.id.name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleListViewHolder {
        return PeopleListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_people_list, parent, false))
    }

    override fun onBindViewHolder(holder: PeopleListViewHolder, position: Int) {
        with(people[position]) {
            holder.name.text = holder.name.context
                .getString(R.string.name_number_combination, fullName, id)
        }
    }

    override fun getItemCount(): Int = people.size

    fun addPeople(items: List<Person>) {
        val currentOffset = itemCount
        people.addAll(items)
        notifyItemRangeChanged(currentOffset + 1, itemCount)
    }
}