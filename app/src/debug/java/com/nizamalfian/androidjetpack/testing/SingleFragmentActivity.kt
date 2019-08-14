package com.nizamalfian.androidjetpack.testing

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import android.widget.FrameLayout.LayoutParams.MATCH_PARENT
import android.view.Gravity.CENTER
import androidx.fragment.app.Fragment
import com.nizamalfian.androidjetpack.R

/**
 * Created by nizamalfian on 01/07/2019.
 */
class SingleFragmentActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val content=FrameLayout(this).also {
            it.layoutParams=FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT, CENTER)
            it.id=R.id.container
        }
        setContentView(content)
    }

    fun setFragment(fragment:Fragment){
        supportFragmentManager
            .beginTransaction()
            .add(R.id.container,fragment,"TEST")
            .commit()
    }

    fun replaceFragment(fragment:Fragment){
        supportFragmentManager
            .beginTransaction()
            .add(R.id.container,fragment)
            .commit()
    }

}