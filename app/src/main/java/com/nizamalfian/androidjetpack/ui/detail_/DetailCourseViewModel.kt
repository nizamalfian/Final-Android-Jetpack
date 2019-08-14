package com.nizamalfian.androidjetpack.ui.detail_

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.nizamalfian.androidjetpack.data.MovieRepository
import com.nizamalfian.androidjetpack.data.local.entity.CourseWithModule
import com.nizamalfian.androidjetpack.data.vo.Resource

/**
 * Created by L
 *
 * on 6/29/2019
 */
class DetailCourseViewModel(private val academyRepository: MovieRepository):ViewModel() {
    private val courseId = MutableLiveData<String>()
    val courseModule:LiveData<Resource<CourseWithModule>> =
        Transformations.switchMap(courseId){courseId->academyRepository.getCourseWithModule(courseId)}

    fun setCourseId(courseId:String){
        this.courseId.value=courseId
    }

    fun getCourseId():String?=courseId.value

    fun setBookmark(){
        if(courseModule.value!=null){
            val courseWithModule = courseModule.value?.data
            if(courseWithModule!=null){
                val courseEntity=courseWithModule.course
                courseEntity?.let{
                    val newState=!it.isBookmarked
                    academyRepository.setCourseBookmark(courseEntity,newState)
                }
            }
        }
    }
}