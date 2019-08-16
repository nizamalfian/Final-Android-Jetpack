package com.nizamalfian.androidjetpack.ui.academy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.nizamalfian.androidjetpack.data.MovieRepository
import com.nizamalfian.androidjetpack.data.local.entity.CourseEntity
import com.nizamalfian.androidjetpack.data.vo.Resource

/**
 * Created by L
 *
 * on 6/29/2019
 */
class AcademyViewModel(private val academyRepository: MovieRepository):ViewModel() {
    private val login = MutableLiveData<String>()

    fun setUsername(username:String){
        login.postValue(username)
    }

    fun getCourses():LiveData<Resource<List<CourseEntity>>> = Transformations.switchMap(login){ academyRepository.getAllCourses()}
}