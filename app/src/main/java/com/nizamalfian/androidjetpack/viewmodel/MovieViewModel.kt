package com.nizamalfian.androidjetpack.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.nizamalfian.androidjetpack.data.MovieRepository
import com.nizamalfian.androidjetpack.data.local.entity.MovieEntity
import com.nizamalfian.androidjetpack.data.vo.Resource

/**
 * Created by L
 *
 * on 8/13/2019
 */
class MovieViewModel(private val academyRepository: MovieRepository): ViewModel() {
    private val login = MutableLiveData<String>()

    fun setUsername(username:String){
        login.value=username
    }

    fun getMovies(): LiveData<Resource<List<MovieEntity>>> = Transformations.switchMap(login){ academyRepository.getAllMovies()}
}