package com.nizamalfian.androidjetpack.base

import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.nizamalfian.androidjetpack.R

/**
 * Created by nizamalfian on 03/08/2019.
 */
abstract class BaseActivity(@LayoutRes private val layout:Int):AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)

        findViewById<Toolbar>(R.id.toolbar)?.let{
            setSupportActionBar(it)
            supportActionBar?.setDisplayShowHomeEnabled(true)
        }

        init()
        setData()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    abstract fun init()
    abstract fun setData()
}