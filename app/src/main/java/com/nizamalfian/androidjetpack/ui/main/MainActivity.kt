package com.nizamalfian.androidjetpack.ui.main

import android.widget.FrameLayout
import androidx.appcompat.widget.Toolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nizamalfian.androidjetpack.R
import com.nizamalfian.androidjetpack.base.BaseActivity
import com.nizamalfian.androidjetpack.ui.favorite.FavoriteFragment
import com.nizamalfian.androidjetpack.ui.movie.MovieFragment
import com.nizamalfian.androidjetpack.ui.tvshow.TVShowFragment
import com.nizamalfian.androidjetpack.base.BaseContentFragment.Companion.attach as attachContentFragment

/**
 * Created by nizamalfian on 02/08/2019.
 */
class MainActivity:BaseActivity(R.layout.activity_main) {
    private lateinit var frameLayout:FrameLayout
    private lateinit var bottomNavigation:BottomNavigationView
    private lateinit var toolbar:Toolbar
    private val fragmentIds = intArrayOf(R.id.menuItemMovie,R.id.menuItemTVShow,R.id.menuItemFavorite)

    override fun init() {
        toolbar = findViewById(R.id.toolbar)
        frameLayout = findViewById(R.id.frameLayout)
        bottomNavigation = findViewById(R.id.nav_view)
    }

    override fun setData() {
        bottomNavigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        bottomNavigation.selectedItemId=fragmentIds[0]
    }

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            fragmentIds[0] -> {
                supportActionBar?.title=getString(R.string.movie)
                attachContentFragment(this,frameLayout,MovieFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            fragmentIds[1] -> {
                supportActionBar?.title=getString(R.string.tv_show)
                attachContentFragment(this,frameLayout,TVShowFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            fragmentIds[2] -> {
                supportActionBar?.title=getString(R.string.favorite)
                FavoriteFragment.attach(this,frameLayout)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
}