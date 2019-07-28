package app.interactive.academy.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import app.interactive.academy.data.AcademyRepository
import app.interactive.academy.data.source.local.entity.CourseEntity
import app.interactive.academy.data.source.local.entity.CourseWithModule
import app.interactive.academy.data.source.local.entity.ModuleEntity
import app.interactive.academy.data.source.vo.Resource
import app.interactive.academy.utils.generateDummyCourses
import app.interactive.academy.utils.generateDummyModules

/**
 * Created by L
 *
 * on 6/29/2019
 */
class DetailCourseViewModel(private val academyRepository: AcademyRepository):ViewModel() {
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