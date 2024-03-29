package com.nizamalfian.androidjetpack.ui.home

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import com.nizamalfian.androidjetpack.R
import com.nizamalfian.androidjetpack.ui.academy.AcademyFragment
import com.nizamalfian.androidjetpack.ui.bookmark.BookmarkFragment

class HomeActivity : AppCompatActivity() {
    private lateinit var navView:BottomNavigationView

    private val SELECTED_MENU="SELECTED_MENU"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        navView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(navigationItemSelectedListener)

        when(savedInstanceState!=null){
            true->savedInstanceState.getInt(SELECTED_MENU)
            false-> navView.selectedItemId = R.id.action_home
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putInt(SELECTED_MENU,navView.selectedItemId)
    }

    private val navigationItemSelectedListener=BottomNavigationView.OnNavigationItemSelectedListener {
        R.id.container.run {
            when(it.itemId){
                R.id.action_home->{
                    AcademyFragment.attach(this@HomeActivity,this)
                }
                R.id.action_bookmark->{
                    BookmarkFragment.attach(this@HomeActivity,this)
                }
            }
        }
        true
    }
}
