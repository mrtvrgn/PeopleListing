package com.mrtvrgn.practice.peoplelisting

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mrtvrgn.practice.peoplelisting.screen.people.PeopleListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.setHomeButtonEnabled(true)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, PeopleListFragment())
            .commit()
    }
}