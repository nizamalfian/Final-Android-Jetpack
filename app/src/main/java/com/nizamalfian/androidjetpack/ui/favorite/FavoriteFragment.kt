package com.nizamalfian.androidjetpack.ui.favorite

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.nizamalfian.androidjetpack.R
import com.nizamalfian.androidjetpack.base.BaseContentFragment
import com.nizamalfian.androidjetpack.base.BaseFragment
import com.nizamalfian.androidjetpack.viewmodel.TVShowViewModel

/**
 * Created by L
 *
 * on 8/13/2019
 */
class FavoriteFragment :BaseFragment(R.layout.fragment_favorite) {
    companion object{
        fun newInstance(): FavoriteFragment = FavoriteFragment()
        fun attach(activity: AppCompatActivity, frameLayout: FrameLayout, fragment: FavoriteFragment = newInstance()){
            activity.supportFragmentManager
                .beginTransaction()
                .replace(frameLayout.id,fragment)
                .commit()
        }
    }

    override fun init(view: View) {

    }
}