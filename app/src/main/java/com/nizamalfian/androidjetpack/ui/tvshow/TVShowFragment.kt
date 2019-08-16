package com.nizamalfian.androidjetpack.ui.tvshow

import android.os.Bundle
import com.nizamalfian.androidjetpack.base.BaseContentFragment
import com.nizamalfian.androidjetpack.viewmodel.TVShowViewModel

/**
 * Created by L
 *
 * on 8/13/2019
 */
class TVShowFragment: BaseContentFragment<TVShowViewModel>() {
    companion object{
        fun newInstance(isFavorite: Boolean=false): TVShowFragment = TVShowFragment().also {
            it.arguments = Bundle().also { bundle ->
                bundle.putBoolean(BUNDLE_IS_FAVORITE,isFavorite)
            }
        }
    }

    override fun createViewModel(): Class<TVShowViewModel> = TVShowViewModel::class.java

    override fun setData() {
    }
}