package com.nizamalfian.androidjetpack.base

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nizamalfian.androidjetpack.R
import com.nizamalfian.androidjetpack.ui.adapter.MovieAdapter
import com.nizamalfian.androidjetpack.viewmodel.ViewModelFactory

/**
 * Created by nizamalfian on 02/08/2019.
 */
abstract class BaseContentFragment<viewModel:ViewModel>:BaseFragment(R.layout.fragment_content) {
    protected var isFavorite:Boolean=false
    protected lateinit var viewModel:viewModel
    protected lateinit var adapter: MovieAdapter
    protected lateinit var recyclerView: RecyclerView
    protected lateinit var loading: ProgressBar
    protected lateinit var empty: TextView

    companion object{
        const val BUNDLE_IS_FAVORITE="BUNDLE_IS_FAVORITE"

        fun attach(activity: AppCompatActivity, frameLayout: FrameLayout, fragment: Fragment) {
            activity.supportFragmentManager
                .beginTransaction()
                .replace(frameLayout.id, fragment)
                .commit()
        }
    }

    private fun createComponent(){
        this.viewModel= ViewModelProviders.of(this, ViewModelFactory.getInstance((activity as Activity).application))[createViewModel()]
        this.adapter=createAdapter()
    }

    abstract fun createViewModel():Class<viewModel>

    protected fun createAdapter(): MovieAdapter =
        MovieAdapter { movieId, isTVShow ->
            activity?.let {
//                ContentDetailActivity.launch(it, movieId, isTVShow)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createComponent()
        isFavorite=arguments?.getBoolean(BUNDLE_IS_FAVORITE) ?: false
    }

    override fun init(view: View) {
        recyclerView = view.findViewById(R.id.recyclerView)
        loading = view.findViewById(R.id.loading)
        empty = view.findViewById(R.id.txtEmpty)

        recyclerView.apply{
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter=this@BaseContentFragment.adapter
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setData()
    }

    abstract fun setData()
}