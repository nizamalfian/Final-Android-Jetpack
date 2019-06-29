package app.interactive.academy.viewmodel

import androidx.lifecycle.ViewModel
import app.interactive.academy.data.CourseEntity
import app.interactive.academy.data.ModuleEntity
import app.interactive.academy.utils.generateDummyCourses
import app.interactive.academy.utils.generateDummyModules

/**
 * Created by L
 *
 * on 6/29/2019
 */
class DetailCourseViewModel:ViewModel() {
    var courseId:String?=null

    fun getCourse():CourseEntity?{
        generateDummyCourses().forEach {
            if(it.courseId == courseId){
                return it
            }
        }
        return null
    }

    fun getModules():List<ModuleEntity> = generateDummyModules(courseId?:"")

}