package app.interactive.academy.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.interactive.academy.data.AcademyRepository
import app.interactive.academy.data.source.local.entity.CourseEntity
import app.interactive.academy.data.source.local.entity.ModuleEntity
import app.interactive.academy.utils.generateDummyCourses
import app.interactive.academy.utils.generateDummyModules

/**
 * Created by L
 *
 * on 6/29/2019
 */
class DetailCourseViewModel(private val academyRepository: AcademyRepository):ViewModel() {
    var courseId:String?=null

    fun getCourse(): LiveData<CourseEntity>{
        return academyRepository.getCourseWithModule(courseId?:"")
    }

    fun getModules():LiveData<ArrayList<ModuleEntity>> = academyRepository.getAllModulesByCourse(courseId?:"")

    /*fun getCourse(): CourseEntity?{
        generateDummyCourses().forEach {
            if(it.courseId == courseId){
                return it
            }
        }
        return null
    }

    fun getModules():List<ModuleEntity> = generateDummyModules(courseId?:"")*/

}