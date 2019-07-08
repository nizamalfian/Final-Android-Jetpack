package app.interactive.academy.ui.detail

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

    fun getCourse(): CourseEntity?{
        return academyRepository.getCourseWithModule(courseId?:"")
    }

    fun getModules():List<ModuleEntity> = academyRepository.getAllModulesByCourse(courseId?:"")

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