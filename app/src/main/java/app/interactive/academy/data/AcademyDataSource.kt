package app.interactive.academy.data

import app.interactive.academy.data.source.local.entity.CourseEntity
import app.interactive.academy.data.source.local.entity.ModuleEntity
import app.interactive.academy.data.source.remote.response.CourseResponse

/**
 * Created by L
 *
 * on 7/8/2019
 */
interface AcademyDataSource {
    fun getAllCourses():List<CourseResponse>
    fun getCourseWithModule(courseId:String):CourseEntity?
    fun getAllModulesByCourse(courseId:String):List<ModuleEntity>
    fun getBookmarkedCourses():List<CourseEntity>
    fun getContent(courseId: String,moduleId:String):ModuleEntity?
}