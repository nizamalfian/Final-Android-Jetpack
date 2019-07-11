package app.interactive.academy.data

import androidx.lifecycle.LiveData
import app.interactive.academy.data.source.local.entity.CourseEntity
import app.interactive.academy.data.source.local.entity.ModuleEntity

/**
 * Created by L
 *
 * on 7/8/2019
 */
interface AcademyDataSource {
    fun getAllCourses():LiveData<ArrayList<CourseEntity>>
    fun getCourseWithModule(courseId:String):LiveData<CourseEntity>
    fun getAllModulesByCourse(courseId:String):LiveData<ArrayList<ModuleEntity>>
    fun getBookmarkedCourses():LiveData<ArrayList<CourseEntity>>
    fun getContent(courseId: String,moduleId:String):LiveData<ModuleEntity>
}