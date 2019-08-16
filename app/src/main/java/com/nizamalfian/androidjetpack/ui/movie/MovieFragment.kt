package com.nizamalfian.androidjetpack.ui.movie

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nizamalfian.androidjetpack.R
import com.nizamalfian.androidjetpack.base.BaseContentFragment
import com.nizamalfian.androidjetpack.viewmodel.MovieViewModel
import com.nizamalfian.androidjetpack.viewmodel.ViewModelFactory
import com.nizamalfian.androidjetpack.data.vo.Status.*
import com.nizamalfian.androidjetpack.ui.adapter.MovieAdapter
import com.nizamalfian.androidjetpack.utils.gone
import com.nizamalfian.androidjetpack.utils.visible

/**
 * Created by L
 *
 * on 8/13/2019
 */
class MovieFragment: Fragment() {
    private lateinit var viewModel: MovieViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var loading: ProgressBar
    private lateinit var empty: TextView
    private lateinit var adapter: MovieAdapter
    private var isFavorite:Boolean=false

    companion object {
        const val TAG="checkmov"
        const val BUNDLE_IS_FAVORITE="BUNDLE_IS_FAVORITE"
        fun newInstance(isFavorite: Boolean=false): MovieFragment = MovieFragment().also {
            it.arguments = Bundle().also { bundle ->
                bundle.putBoolean(BUNDLE_IS_FAVORITE,isFavorite)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isFavorite=arguments?.getBoolean(BaseContentFragment.BUNDLE_IS_FAVORITE) ?: false
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_content, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerView)
        loading = view.findViewById(R.id.loading)
        empty = view.findViewById(R.id.txtEmpty)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let{ fragmentActivity ->
            viewModel=
                ViewModelProviders.of(fragmentActivity, ViewModelFactory.getInstance(fragmentActivity.application)).get(MovieViewModel::class.java)

            recyclerView.apply {
                layoutManager = LinearLayoutManager(fragmentActivity)
                setHasFixedSize(true)
                this@MovieFragment.adapter = MovieAdapter { movieId, isTVShow ->
//                    Detail
                }.also {
                    adapter = it
                }
            }

            viewModel.also {vm->
                if(isFavorite){
                    Log.d(TAG,"favorite")

                }else{
                    Log.d(TAG,"not favorite")
                    vm.setUsername("nizamalfian")
                    vm.getMovies().observe(fragmentActivity, Observer {
                        when(it){
                            LOADING -> {
                                Log.d(TAG,"Observe loading")
                                loading.visible()
                            }
                            SUCCESS -> {
                                Log.d(TAG,"Observe success")
                                it.data?.let{movies->adapter.update(movies)}
                                loading.gone()
                            }
                            ERROR -> {
                                Log.d(TAG,"Observe error")
                                Toast.makeText(activity,getString(R.string.something_wrong_happened), Toast.LENGTH_SHORT).show()
                                loading.gone()
                            }
                        }
                    })
                }
            }
        }
    }
}